package jobErrorTest.popup.actions;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class NewAction implements IObjectActionDelegate {

	private final class Fred extends Job {
		private final String number;

		private Fred(String name, String number) {
			super(name);
			this.number = number;
		}

		protected org.eclipse.core.runtime.IStatus run(
				org.eclipse.core.runtime.IProgressMonitor monitor) {
			return new Status(IStatus.ERROR, "Test "
					+ number, IStatus.ERROR, "Error "
					+ number, new NullPointerException());
		}
	}

	/**
	 * Constructor for Action1.
	 */
	public NewAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {

		for (int i = 0; i < 100; i++) {
			final String jobNumber = String.valueOf(i);
			Job job1 = new Fred("Job " + jobNumber, jobNumber);
			job1.schedule(i * 10);
		}
		for (int i = 0; i < 100; i++) {
			final String jobNumber = String.valueOf(i);
			Job job1 = new Job("Job " + jobNumber) {
				protected org.eclipse.core.runtime.IStatus run(
						org.eclipse.core.runtime.IProgressMonitor monitor) {
					throw new NullPointerException();
					
				};
			};
			job1.schedule(i * 10);
		}
		

	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
