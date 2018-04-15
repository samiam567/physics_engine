package hesi_exit_exam_analyzer;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2923044527887437775L;
	private String name;
	private Exam[] exams = new Exam[0];
	
	public Student(String studentName) {
		name = studentName;
	}
	
	public void addExam(Exam newExam) {		
		Exam[] examsTemp = exams;
		
		exams = new Exam[exams.length + 1];
		
		for (int i = 0; i < examsTemp.length; i++) {
			exams[i] = examsTemp[i];
		}
		exams[examsTemp.length] = newExam;	
	}
	
	public String getName() {
		return name;
	}
	
	public Exam[] getExams() {
		return exams;
	}
	
	public Exam getExam(String examName) {
		for (Exam cE : exams) {
			if (cE.getName() == examName) {
				return cE;
			}
		}
		
		return null;
		
	}
}
