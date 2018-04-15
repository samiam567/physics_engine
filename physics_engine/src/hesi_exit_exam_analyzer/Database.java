package hesi_exit_exam_analyzer;

import java.io.Serializable;

public class Database implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2774472067783077747L;
	private Student[][] Students = new Student[1][1];
	
	public Student[][] getStudents() {
		return Students;
	}
	
	public Student getStudent(int year, int nameIndx) {
		try {
			return Students[year][nameIndx];
		}catch(ArrayIndexOutOfBoundsException a) {
			return null;
		}
	}
	

	
	public void addStudent(Student newStudent, int year, int nameIndx) {
		try {
			Students[year][nameIndx] = newStudent;
		}catch(ArrayIndexOutOfBoundsException a) {
			System.out.println("Resizing Database... please wait");
			Student[][] StudentsTemp = Students;
					
			//resizing
			if (year >= Students.length ) {
				Students = new Student[year+1][Students[0].length];
			}
			
			if (nameIndx > Students[0].length) {
				Students = new Student[Students.length][nameIndx+1];
			}
			
			//filling in values
			for (int i = 0; i < StudentsTemp.length; i++) {
				for (int q = 0; q < StudentsTemp[0].length; q++) {
					Students[i][q] = StudentsTemp[i][q];
				}
			}
			
			Students[year][nameIndx] = newStudent; //adding in the new student
		}
	}
}
