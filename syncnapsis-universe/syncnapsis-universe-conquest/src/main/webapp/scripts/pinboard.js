/**
 * Syncnapsis Framework - Copyright (c) 2012 ultimate
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version
 * 3 of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MECHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Plublic License along with this program;
 * if not, see <http://www.gnu.org/licenses/>.
 */
//@requires("Server")

/*
 * This a view Entity for a Pinboard identified by its ID
 * (Messages are sorted newest first!) 
 */
Pinboard = function(container, pinboardIdOrName, style, removeOldMessages, disableInput)
{
	this.pinboard = null;
	this.messages = [];
	this.messageCount = 0; 
	this.removeOldMessages = removeOldMessages;
	this.disableInput = disableInput;
	this.container = container;
	this.style = style;
	
	// start - init view
	{
		// set style
		this.container.classList.add("pinboard");
		this.container.classList.add(style);
		// add container for messages
		this.messageContainer = document.createElement("div");
		this.messageContainer.classList.add("messages");
		this.container.appendChild(this.messageContainer);
		// add input area
		if(!this.disableInput)
		{
			this.inputContainer = document.createElement("div");
			
			this.titleInput = document.createElement("input");
			this.titleInput.type = "text";
			var title = document.createElement("div");
			title.classList.add("title");
			title.appendChild(this.titleInput);
			this.inputContainer.appendChild(title);
			
			this.contentInput = document.createElement("textarea");
			var content = document.createElement("div");
			content.classList.add("content");
			content.appendChild(this.contentInput);
			this.inputContainer.appendChild(content);
			
			this.postButton = document.createElement("div");
			var a = document.createElement("a");
			a.appendChild(document.createTextNode(">")); // TODO
			this.postButton.appendChild(a);
			this.postButton.classList.add("frame");
			this.postButton.classList.add("button");
			this.inputContainer.appendChild(this.postButton);
			
			this.inputContainer.classList.add("input");
			this.container.appendChild(this.inputContainer);
		}
	}
	// end - init view
	
	this.setStyle = function(style)
	{
		this.container.classList.remove(this.style);
		this.container.classList.add(style);
		this.style = style;
	}
	
	this.setUser = function(user)
	{
		this.user = user;
		
		if(this.user && !this.disableInput)
		{
			this.postButton.classList.remove("disabled");
			this.postButton.children[0].onclick = function(pinboard) {
				return function() {
					pinboard.post(pinboard.titleInput.value, pinboard.contentInput.value);
				};
			} (this);
			this.titleInput.disabled = false;
			this.contentInput.disabled = false;
		}
		else
		{
			this.postButton.classList.add("disabled");
			this.postButton.children[0].onclick = null;
			this.titleInput.disabled = true;
			this.contentInput.disabled = true;
		}
		// TODO lock/unlock
	};
	
	this.addMessage = function(message)
	{
		// add message at right position
		var i;
		for(i = 0; i < this.messages.length; i++)
		{
			if(this.messages[i].messageId < message.messageId)
				break;
		}
		this.messages.splice(i, 0, message);
		
		// define a callback for creating the message element
		var createElement = function(pinboard)
		{
			return function()
			{
				// create dom element
				message.element = document.createElement("div");
				message.element.classList.add("message");
				
				var df = (pinboard.user != null ? pinboard.user.dateFormat : UI.constants.DEFAULT_DATE_FORMAT);
				
				var time = document.createElement("div");
				time.classList.add("time");
				time.appendChild(document.createTextNode(new Date(message.creationDate).format(df)));
				
				var user = document.createElement("div");
				user.classList.add("user");
				user.appendChild(document.createTextNode(message.creator.username));
				
				var title = document.createElement("div");
				title.classList.add("title");
				title.appendChild(document.createTextNode(message.title ? message.title : ""));
				
				var content = document.createElement("div");
				content.classList.add("content");
				content.appendChild(document.createTextNode(message.content));
				
				message.element.appendChild(time);
				message.element.appendChild(user);
				message.element.appendChild(title);
				message.element.appendChild(content);
				
				pinboard.messageContainer.insertBefore(message.element, pinboard.messageContainer.children[i]);
			}
		} (this);
		
		// load the user for the message
		var u = server.entityManager.get(Types.User, message.creator.id);
		if(u == null)
		{
			server.entityManager.load(message.creator, createElement);
		}
		else
		{
			message.creator = u;
			(createElement)(message);
		}
	};
	
	this.removeMessage = function(messageId)
	{
		var index = null;
		for(var i = 0; i < this.messages.length; i++)
		{
			if(this.messages[i].messageId == messageId)
			{
				index = i;
				break;
			}
		}
		if(index != null)
		{
			this.messageContainer.removeChild(this.messages[index].element);
			this.messages.splice(index, 1);
		}
	};
	
	this.checkForMissingMessages = function()
	{
		var from = null;
		var to = null;
		// check for missing messages
		for(var i = 1; i < this.messages.length; i++)
		{
			if(this.messages[i].messageId != this.messages[i-1].messageId - 1)
			{
				// messageId not consecutive
				if(from == null || from > this.messages[i].messageId)
					from = this.messages[i].messageId;
				if(to == null || to < this.messages[i].messageId)
					to = this.messages[i].messageId;
			}
		}
		if(from != null && to != null)
			this.requestUpdate(from, to);
	};
	
	this.post = function(title, message)
	{
		if(this.user == null)
			return false; // ignore
		if(message == null || message == "")
			return false; // ignore
		server.messageManager.postPinboardMessage(this.pinboard.id, title, message);
		return true;
	};
	
	this.update = function(messages)
	{
		for(var i = 0; i < messages.length; i++)
		{
			this.addMessage(messages[i]);
		}

		if(this.messages.length < this.messageCount)
		{
			this.checkForMissingMessages();
		}
		if(this.removeOldMessages)
		{
			while(this.messages.length > this.messageCount)
			{
				// remove the last message
				this.removeMessage(this.messages[this.messages.length-1].messageId);
			}
		}
	};
	
	this.requestUpdate = function(from, to)
	{
		if(from == null || to == null)
			server.messageManager.requestPinboardUpdate(this.pinboard.id, this.messageCount);
		else
			server.messageManager.requestPinboardUpdate(this.pinboard.id, from, to);
	};
	
	this.setMessageCount = function(messageCount)
	{
		this.messageCount = messageCount;
		this.requestUpdate();
	};
	
	this.init = function(pinboard)
	{
		this.pinboard = pinboard;
		this.messageCount = this.pinboard.defaultMessageCount;
		this.requestUpdate();
	};
	
	this.setUser(null);	
	
	// associate this view with a pinboard loaded by name or id
	if(typeof(pinboardIdOrName) == Reflections.type.STRING)
		server.pinboardManager.getByName(pinboardIdOrName, Events.wrapEventHandler(this, this.init));
	else
		server.pinboardManager.get(pinboardIdOrName, Events.wrapEventHandler(this, this.init));
};