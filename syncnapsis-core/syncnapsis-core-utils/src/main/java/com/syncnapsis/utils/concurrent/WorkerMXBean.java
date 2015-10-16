package com.syncnapsis.utils.concurrent;

/**
 * MXBean-Interface for Workers.
 * All relevant Methods for configuring the Workers are declared here.
 * 
 * @author ultimate
 */
public interface WorkerMXBean
{

	/**
	 * Start this worker.<br>
	 * Exactly like <code>worker.start(false);</code>
	 * 
	 * @see WorkerMXBean#start(boolean)
	 * @see WorkerMXBean#isRunning()
	 * @see WorkerMXBean#isSuspended()
	 */
	public void start();

	/**
	 * Start this worker - if desired in suspended state<br>
	 * Other than calling {@link WorkerMXBean#start()} and {@link WorkerMXBean#suspend()}
	 * subsequently this call
	 * will guarantee suspended state from the beginning without any change of the worker to be
	 * executed before suspend(..) is called.
	 * 
	 * @see WorkerMXBean#start()
	 * @see WorkerMXBean#suspend()
	 */
	public void start(boolean suspended);

	/**
	 * Stop this worker
	 * 
	 * @see WorkerMXBean#isRunning()
	 * @see WorkerMXBean#isSuspended()
	 */
	public void stop();

	/**
	 * Suspend this worker
	 * 
	 * @see WorkerMXBean#isRunning()
	 * @see WorkerMXBean#isSuspended()
	 */
	public void suspend();

	/**
	 * Resume this worker
	 * 
	 * @see WorkerMXBean#isRunning()
	 * @see WorkerMXBean#isSuspended()
	 */
	public void resume();

	/**
	 * Get the (optional) interval for executing {@link WorkerMXBean#work(long)}.<br>
	 * If interval is set to 0 the worker will immediately execute {@link WorkerMXBean#work(long)}
	 * continuously without delay.
	 * 
	 * @see WorkerMXBean#setInterval(long)
	 * @return interval
	 */
	public long getInterval();

	/**
	 * Update the (optional) interval for executing {@link WorkerMXBean#work(long)}.<br>
	 * If interval is set to 0 the worker will immediately execute {@link WorkerMXBean#work(long)}
	 * continuously without delay.<br>
	 * <b>Note:</b> the interval change will be applied for the execution immediately
	 * 
	 * @see WorkerMXBean#getInterval(long)
	 * @param interval - the new interval to set
	 */
	public void setInterval(long interval);

	/**
	 * Is this worker currently running?<br>
	 * <b>Note:</b> will even return true when worker is suspended.
	 * 
	 * @see WorkerMXBean#isSuspended()
	 * @return running
	 */
	public boolean isRunning();

	/**
	 * Is this worker currently suspended?<br>
	 * <b>Note:</b> will even return true when worker is suspended.
	 * 
	 * @see WorkerMXBean#isSuspended()
	 * @return suspended
	 */
	public boolean isSuspended();

	/**
	 * Flag signaling that an error has occurred.
	 * 
	 * @return error-flag
	 */
	public boolean hasError();

	/**
	 * The error that has occurred.
	 * 
	 * @return errorCause
	 */
	public Throwable getErrorCause();

	/**
	 * Clear the error-flag and errorCause
	 * 
	 * @see WorkerMXBean#hasError()
	 * @see WorkerMXBean#getErrorCause()
	 */
	public void clearError();

	/**
	 * Flag signaling that a warning has occurred.
	 * 
	 * @return
	 */
	public boolean hasWarning();

	/**
	 * The warning that has occurred.
	 * 
	 * @param warningCause
	 */
	public Throwable getWarningCause();

	/**
	 * Clear the warning-flag and warningCause
	 * 
	 * @see WorkerMXBean#hasWarning()
	 * @see WorkerMXBean#getWarningCause()
	 */
	public void clearWarning();
}
