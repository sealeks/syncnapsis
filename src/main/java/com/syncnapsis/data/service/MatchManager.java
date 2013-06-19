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
package com.syncnapsis.data.service;

import java.util.Date;
import java.util.List;

import com.syncnapsis.data.model.Match;
import com.syncnapsis.data.model.Participant;
import com.syncnapsis.enums.EnumJoinType;
import com.syncnapsis.enums.EnumStartCondition;
import com.syncnapsis.enums.EnumVictoryCondition;
import com.syncnapsis.utils.data.ExtendedRandom;

/**
 * Manager-Interface for access to Match.
 * 
 * @author ultimate
 */
public interface MatchManager extends GenericNameManager<Match, Long>
{
	/**
	 * Get the list of matches for a creator for the given reference date:<br>
	 * This will mean the match state (planned, active, finished, canceled) will be determined for
	 * this timestamp.
	 * 
	 * @param creatorId - the id of the creator
	 * @param planned - include planned matches?
	 * @param active - include active matches?
	 * @param finished - include finished matches?
	 * @param canceled - include canceled matches?
	 * @return the list of matches
	 */
	public List<Match> getByCreator(long creatorId, boolean planned, boolean active, boolean finished, boolean canceled);

	/**
	 * Get the list of matches for a player for the given reference date:<br>
	 * This will mean the match state (planned, active, finished, canceled) will be determined for
	 * this timestamp.
	 * 
	 * @param playerId - the id of the player
	 * @param planned - include planned matches?
	 * @param active - include active matches?
	 * @param finished - include finished matches?
	 * @param canceled - include canceled matches?
	 * @return the list of matches
	 */
	public List<Match> getByPlayer(long playerId, boolean planned, boolean active, boolean finished, boolean canceled);

	/**
	 * Get the list of matches for a galaxy for the given reference date:<br>
	 * This will mean the match state (planned, active, finished, canceled) will be determined for
	 * this timestamp.
	 * 
	 * @param galaxyId - the id of the galaxy
	 * @param planned - include planned matches?
	 * @param active - include active matches?
	 * @param finished - include finished matches?
	 * @param canceled - include canceled matches?
	 * @return the list of matches
	 */
	public List<Match> getByGalaxy(long galaxyId, boolean planned, boolean active, boolean finished, boolean canceled);

	/**
	 * Create a new match and start it if the startCondition demands it.
	 * 
	 * @see EnumStartCondition
	 * @param title - the title of the match
	 * @param galaxyId - the galaxy for the match
	 * @param speed - the speed of the match
	 * @param seed - an optional seed for the match
	 * @param startCondition - the start condition
	 * @param startDate - the start date (if required for the start condition)
	 * @param startSystemSelectionEnabled - is start system selection enabled?
	 * @param startSystemCount - the number of start systems for each participant
	 * @param startPopulation - the total population for all start systems
	 * @param victoryCondition - the victory condition
	 * @param victoryParameter - the victory parameter
	 * @param participantsMax - the max number of participants
	 * @param participantsMin - the min number of participants
	 * @param participantIds - a list of participants invited (as player ids)
	 * @param plannedJoinType - the join type before the match is started
	 * @param startedJoinType - the join type after the match is started
	 * @return the match created
	 */
	public Match createMatch(String title, long galaxyId, int speed, Long seed, EnumStartCondition startCondition, Date startDate,
			boolean startSystemSelectionEnabled, int startSystemCount, int startPopulation, EnumVictoryCondition victoryCondition,
			int victoryParamter, int participantsMax, int participantsMin, List<Long> participantIds, EnumJoinType plannedJoinType,
			EnumJoinType startedJoinType);

	/**
	 * Force the start of the given match no matter if the start condition is met or not.<br>
	 * <br>
	 * All required settings and calculations required prior to starting the match will be made.
	 * This will include randomly associating the rivals in vendetta-mode as well as marking all
	 * populations chosen as start populations with the first colonization date and select ramdom
	 * start systems for all participants without start systems chosen. If necessary the match will
	 * furthermore be registered for calculation services etc.<br>
	 * <br>
	 * Starting the match will perform a security check wether the calling user is equal to the
	 * creator or if the match is started by the system itself.
	 * 
	 * @param match - the match to start
	 * @return the match entity
	 */
	public Match startMatch(Match match);

	/**
	 * Start the given match if the start condition is met.<br>
	 * 
	 * @see MatchManager#startMatch(Match)
	 * @param match - the match to start
	 * @return the match entity
	 */
	public Match startMatchIfNecessary(Match match);

	/**
	 * Cancel a planned game if it has not yet been started. This will include cleaning up all
	 * remaining data like start system selections, associated participants, initialized galaxies
	 * and more...<br>
	 * <br>
	 * Canceling the match will perform a security check wether the calling user is equal to the
	 * creator.
	 * 
	 * @param match - the match to cancel
	 * @return true if the match has been canceled, false otherwise
	 */
	public boolean cancelMatch(Match match);

	/**
	 * Clean up the given match by setting all remaining required properties for the match and all
	 * associated entities. This will include setting finish and or destruction dates etc.
	 * 
	 * @param match - the match to finish
	 * @return the match entity
	 */
	public Match finishMatch(Match match);

	/**
	 * Check wether the victory condition for the given match is met. If the condition is met the
	 * match is ready to be finished by using {@link MatchManager#finishMatch(Match)}
	 * 
	 * @param match - the match to check
	 * @return true or false
	 */
	public boolean isVictoryConditionMet(Match match);

	/**
	 * Update the rankings for the given match.
	 * 
	 * @param match - the match to update
	 * @return the match entity
	 */
	public Match updateRanking(Match match);

	/**
	 * Assign the specified number of rivals to each participant from within the choice of all other
	 * participants within the list. This method guarantees every participant is exactly rival for
	 * the n participants and receives n rivals itself where n is the number of rivals specified.
	 * Furthermore it will guarantee not to assign a rival multiple times to the same participant.<br>
	 * <br>
	 * The algorithm is design in such a way that calling this method twice with the same list of
	 * participants, number of rivals and random number generator the assigned rivals will be
	 * identical. This will also include the case where the order of the participants within the
	 * list has been changed, since this order does not have any influence on the result of the
	 * algorithm.<br>
	 * <br>
	 * <b>Note:</b> The participant entities won't be saved to database - this method only assigns
	 * the rivals as references.
	 * 
	 * @param participants - the number of participants
	 * @param rivals - the number of rivals to assign for each participant
	 * @param random - the ExtendedRandom-number generator
	 * @return the matrix of rival associations
	 */
	public void assignRivals(List<Participant> participants, int rivals, ExtendedRandom random);

	/**
	 * Return the number of rivals to assign for the given number of total participants primarily
	 * used in vedetta mode. This method will currently return zero for all game mode other than
	 * {@link EnumVictoryCondition#vendetta}<br>
	 * 
	 * @param match - the match to get the number of rivals for
	 * @return the number of rivals
	 */
	public int getNumberOfRivals(Match match);
}
