package hesi_exit_exam_analyzer;

public class LoadThread extends Thread{
	private HEEA_scanner scanner;
	
	public LoadThread(HEEA_scanner scan) {
		scanner = scan;
	}
	
	public void run() {
		
		scanner.databaseLoaded = false;
		scanner.loadDatabase();
		scanner.databaseLoaded = true;
	}
}
