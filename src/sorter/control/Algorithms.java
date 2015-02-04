package sorter.control;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import sorter.control.algorithms.SortAlgorithm;
import sorter.view.AnimationWidget;


/**
 * A class that holds the AlgorithmHandlers for all algorithms 
 * and handles synchronization of the algorithms when they are run.
 *
 * This class does not have to be changed
 * 
 * @author Sara Stymne
 *
 */
public class Algorithms {
	
	/**
	 * The vector of integers to be sorted
	 */
	private ArrayList<AlgorithmHandler> algorithms;
	
	/**
	 * The barrier that handles synchronization of algorithms
	 */
	CyclicBarrier barrier;
	
	/**
	 * Thread used to simulate user-stepping when no user
	 * is currently stepping.
	 */
	Thread steppingThread = null;
	
	/**
	 * The status of the algorithms
	 */
	private AlgorithmStatus status;
	
	/**
	 * The time that the animation pauses between each step.
	 * It is initialised to 50 (milliseconds)
	 */
	public int delayTime = 50;
	
	
	/**
	 * If true, the sorting algorithm is currently stepping forward
	 * manually, one step at a time.  Otherwise it just runs
	 * unrestrained.
	 */
	private boolean stepping = false;
	
	/**
	 * Creates a new instance of Algorithms with no algorithms
	 */
	public Algorithms() {
		algorithms = new ArrayList<AlgorithmHandler>();
		status = new AlgorithmStatus();
	}
	
		
	/**
	 * Creates a new AlgorithmHandler which it then adds to itself.
	 * 
	 * @param alg the actual new algorithm
	 * @param vec the sortVector of the new algorithm
	 */
	public void add(SortAlgorithm alg, DataSet vec) {
		algorithms.add(new AlgorithmHandler(alg, vec, this, status));
	}
	
	/**
	 * Updates all AlgorithmHandlers with a copy of a new sort vector.
	 * 
	 * @param sortVec the new sort vector
	 */
	public void setVector(DataSet sortVec) {
		
		for(AlgorithmHandler alg : algorithms) {
			DataSet vectorClone = sortVec.clone();
			alg.setVector(vectorClone);
		}	
	}	
	
	/**
	 * Returns the widget of the last AlgorithmHandler that was added.
	 * 
	 * @return the widget
	 */
	public AnimationWidget getLastWidget() {
		return algorithms.get(algorithms.size()-1).getWidget();
	}
	
	/**
	 * Sets or reset the stepping to/from manual.
	 * 
	 * @param stepping The new value for manual stepping.
	 */
	public void setStepping(boolean stepping) {
		this.stepping = stepping;
	}
	
	/**
	 * Steps the algorithm one step forward.
	 */
	public void step() {
		try {
			await();
		} catch (InterruptedException e) {
			// do nothing
		} catch (BrokenBarrierException e) {
			// do nothing
		}
	}
	
	/**
	 * Starts the animation for all AlgorithmHandlers, 
	 * which includes setting up the barrier.
	 * 
	 * @param swapWeight the swapWeight for this run of the animation
	 */
	public void start(int swapWeight) {
		
		//Gives status the current values
		status.setNumberOfAlgorithms(algorithms.size());
		status.resetFinishedAlgorithms();
		
		// kills any old steppingThreads
		if (steppingThread != null) {
			steppingThread.interrupt();
			steppingThread = null;
		}

		//Creates a new CyclicBarrier
        //Which assures that all algorithms are synchronized
        //Waits delayTime ms between each step of all algorithms
		barrier = new CyclicBarrier(status.getNumberOfAlgorithms()+1,
				new Runnable() {
					public void run() {
						try {
							if (!stepping) {
								Thread.sleep(delayTime);
							}
						} catch (InterruptedException e) {

						}
					}
				});
		
		// creates and starts a new steppingThread
		steppingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						if (!stepping) {
							try {
								await();
							} catch (BrokenBarrierException e) {
								// should not happen
							}
						}
						Thread.sleep(1);
					}
				} catch (InterruptedException e) {
					// do nothing
				}
			}
		}, "Stepping");
		steppingThread.start();
		
		//Adds a reference of the barrier 
		//and the current swapWeight to each algorithm
		for (AlgorithmHandler alg : algorithms) {
			alg.setSwapWeight(swapWeight);
		}
		
		//Starts all the algorithms
		for (AlgorithmHandler alg : algorithms) {
			new Thread(alg).start();
		}
		
	}
	
	/**
	 * Checks if the animation is finished, i.e. if no animation currently runs
	 * 
	 * @return true if the animation is finished, false if it runs
	 */
	public boolean animationFinished() {
		return status.animationFinished();
	} 
	
	
	/**
	 * Sets the delay time 
	 * that decides how long the break between each step of the animation is
	 * 
	 * @param dTime the new delay time
	 */
	public void setDelayTime(int dTime) {
        delayTime = dTime;
	}


	/**
	 * Causes the calling algorithm wait for all other algorithms to catch up
	 * before continuing.
	 * 
	 * @throws InterruptedException
	 * @throws BrokenBarrierException
	 */
	public void await() throws InterruptedException, BrokenBarrierException {
		barrier.await();
	}
}

