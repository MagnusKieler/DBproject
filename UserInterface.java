package projectDB;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;  // Import the Scanner class

public class UserInterface {
	
	static Scanner myScanner;

	public static int SubMenuGroupOfExercises()
	{
		int userChoice = UserInterfaceValidation.GetIntegerInput(SubMenuGroupOfExercisesString(), 1, 6);
		
		return userChoice;
	}
	
	public static int SubMenuEquipment()
	{
		int userChoice = UserInterfaceValidation.GetIntegerInput(SubMenuEquipmentString(), 1, 5);
		
		return userChoice;
	}
	
	public static int SubMenuExercise()
	{
		int userChoice = UserInterfaceValidation.GetIntegerInput(SubMenuExerciseString(), 1, 6);
		
		return userChoice;
	}
	
	public static int SubMenuWorkout()
	{
		int userChoice = UserInterfaceValidation.GetIntegerInput(SubMenuWorkoutString(), 1, 7);
		
		return userChoice;
	}
	 
	
	public static int MainMenu()
	{
		int userChoice = UserInterfaceValidation.GetIntegerInput(MainMenuString(), 1, 5);
		
		return userChoice;
	}
	
	
	public static String MainMenuString()
	{
		String menuInfo = "Main Menu. Please select an option.\n";
		
		menuInfo += "[1] Workout menu\n";
		menuInfo += "[2] Exercise menu\n";
		menuInfo += "[3] Equipment menu\n";
		menuInfo += "[4] Group of exercises menu\n";
		menuInfo += "[5] Exit program\n";
		
		return menuInfo;
		
	}
	
	
	
	public static String SubMenuWorkoutString()
	{
		String menuInfo = "Workout Menu. Please select an option.\n";
		
		menuInfo += "[1] Create new workout\n";
		menuInfo += "[2] View workouts\n";
		menuInfo += "[3] Edit workout\n";
		menuInfo += "[4] Delete workout\n";
		menuInfo += "[5] View n latest workouts\n";
		menuInfo += "[6] View Exercises of workout\n";
		menuInfo += "[7] Exit to Main Menu\n";
		
		return menuInfo;
		
	}
	
	
	public static String SubMenuExerciseString()
	{
		String menuInfo = "Exercise Menu. Please select an option.\n";
		
		menuInfo += "[1] Create new exercise\n";
		menuInfo += "[2] View exercises\n";
		menuInfo += "[3] Edit exercise\n";
		menuInfo += "[4] View average kilos for an exercise\n";
		menuInfo += "[5] Delete exercise\n";
		menuInfo += "[6] Exit to Main Menu\n";
		
		return menuInfo;
		
	}
	

	public static String SubMenuEquipmentString()
	{
		String menuInfo = "Equipment Menu. Please select an option.\n";
		
		menuInfo += "[1] Create new equipment\n";
		menuInfo += "[2] View equipments\n";
		menuInfo += "[3] Edit equipment\n";
		menuInfo += "[4] Delete equipment\n";
		menuInfo += "[5] Exit to Main Menu\n";
		
		return menuInfo;
		
	}
	
	public static String SubMenuGroupOfExercisesString()
	{
		String menuInfo = "Group of exercises Menu. Please select an option.\n";
		
		menuInfo += "[1] Create new Group of exercises\n";
		menuInfo += "[2] View Group of exercises\n";
		menuInfo += "[3] View exercises with bodypart\n";
		menuInfo += "[4] Edit Group of exercises\n";
		menuInfo += "[5] Delete Group of exercises\n";
		menuInfo += "[6] Exit to Main Menu\n";
		
		return menuInfo;
		
	}
	
	// TODO: Add exercises, validate workout IDs and 
	
	
	

	
	public static void CreateWorkout()
	{
		System.out.println("Create a new workout");
		
		String length = UserInterfaceValidation.GetStringInput("Enter length: ", 40, 1);

	    int personalShape = UserInterfaceValidation.GetIntegerInput("Enter Personal Shape: (An integer between 1 and 10)", 1, 10);
	    
	    int performance = UserInterfaceValidation.GetIntegerInput("Enter Performance: (An integer between 1 and 10)", 1, 10);
	    
	    String date = UserInterfaceValidation.GetDateInput("Enter date 'yyyy-mm-dd'");
	    
	    // Add note to the workout
	    String noteText = "";
	    boolean createNote = false;
	    
	    // Ask for confirmation
	    String confirmation = UserInterfaceValidation.GetConfirmation("Do you want to add a note to the workout? ('y' = yes/'n' = no)");
	 			
		if (UserInterfaceValidation.isConfirmation(confirmation))
		{
			noteText = UserInterfaceValidation.GetStringInput("Enter note text: (Max 200 characters)", 200);
		    		    
		    if (noteText.length() > 0)
		    {
		    	createNote = true;
		    }
		}
		else {
			System.out.println("No confirmed\n");
		}
	    
	    
	    // Add exercises to the workout
	    ArrayList<Integer> listOfExerciseIDs = ExerciseManager.GetListOfExerciseIDs();
	    
	    ArrayList<Integer> listOfExerciseIDsToAdd = new ArrayList<Integer>();
	    
	    // Only offer to add exercises if they exist
	    if (listOfExerciseIDs.size() > 0)
	    {
	    	// Ask for confirmation
			confirmation = UserInterfaceValidation.GetConfirmation("Do you want to add any exercises to the workout? ('y' = yes/'n' = no)");
			
			if (UserInterfaceValidation.isConfirmation(confirmation))
			{

			    listOfExerciseIDsToAdd = UserInterfaceValidation.GetIntegerListInput
			    		("Enter ExerciseIDs to add: (List of Exercise IDs separated by comma. Ex: 1, 2, 3)", listOfExerciseIDs);
				
			}
			else {
				System.out.println("No confirmed\n");
			}
	    }
 
	    try {
			WorkoutManager.CreateWorkout(date, length, personalShape, performance, noteText, createNote, listOfExerciseIDsToAdd);
		} catch (SQLException e) {
			System.out.println("Could not create workout");
			e.printStackTrace();
		}
	}
	
	public static void EditWorkout()
	{
		// Print workouts to make it easier for the user to select
		
		
		ArrayList<Integer> listOfValidWorkoutIDs = WorkoutManager.GetListOfWorkoutIDs();
		
		// Choose a workout to edit
		int workoutID = UserInterfaceValidation.GetIntegerInput("Please enter the ID of the Workout to be edited: ", listOfValidWorkoutIDs);
		
		// Print selected workout
		System.out.println("Selected: " + WorkoutManager.GetWorkout(workoutID));
		
		String length = UserInterfaceValidation.GetStringInput("Enter length: ", 40, 1);

	    int personalShape = UserInterfaceValidation.GetIntegerInput("Enter Personal Shape: (An integer between 1 and 10)", 1, 10);
	    
	    int performance = UserInterfaceValidation.GetIntegerInput("Enter Performance: (An integer between 1 and 10)", 1, 10);
	    
	    String date = UserInterfaceValidation.GetDateInput("Enter date 'yyyy-mm-dd'");
	    
	    String noteText = UserInterfaceValidation.GetStringInput("Enter note text: (Max 200 characters)", 200);

	    boolean createNote = false;
	    
	    if (noteText.length() > 0)
	    {
	    	createNote = true;
	    }
		
		// Edit workout
		WorkoutManager.EditWorkout(workoutID, date, length, personalShape, performance, noteText, createNote);
 
		
		System.out.println("Edited workout");
		System.out.println();
	}
	
	public static void DeleteWorkout()
	{
		// Print list of all workouts
		
		// Select a workout to delete it 
		int workoutID = UserInterfaceValidation.GetIntegerInput("Please enter the ID of the Workout to be deleted: ", 1, 1000);
		
		System.out.println("Selected: " + WorkoutManager.GetWorkout(workoutID));
		
		// Ask for confirmation
		String confirmation = UserInterfaceValidation.GetConfirmation("Are you sure you want to delete this workout? ('y' = yes/'n' = no)");
		
		if (UserInterfaceValidation.isConfirmation(confirmation))
		{
			WorkoutManager.DeleteWorkout(workoutID);
			System.out.println("Deleted workout\n");
		}
		else {
			System.out.println("Did not delete workout\n");
		}
		System.out.println();
	}
	
	public static void ViewWorkouts()
	{		
		System.out.println("View workouts:");
		
		WorkoutManager.PrintWorkouts();
	}
	
	public static void ViewNLatestWorkouts()
	{
		System.out.println("View n latest workouts");
		
		int n = UserInterfaceValidation.GetIntegerInput("Enter number of workouts to view: (Integer between 1 and 100)", 1, 100);
		
		WorkoutManager.PrintNLatestWorkouts(n);
	}
	
	public static void ViewExercisesOfWorkout()
	{
		// Print workouts
		
		ArrayList<Integer> listOfValidWorkoutIDs = WorkoutManager.GetListOfWorkoutIDs();
		
		// Get workout ID
		int workoutID = UserInterfaceValidation.GetIntegerInput("Enter the ID of the workout to view its exercises:", listOfValidWorkoutIDs);
		
		// Print exercises of workout
		
	}
	
	public static void CreateExercise()
	{
		System.out.println("Create Exercise:");
		
		String name = UserInterfaceValidation.GetStringInput("Enter name of exercise: (Max 40 characters)", 40, 1);
		
		String bodypart = UserInterfaceValidation.GetStringInput("Enter bodypart of exercise: (Max 40 characters)", 40, 1);
		
		String description = UserInterfaceValidation.GetStringInput("Enter description of exercise: (Max 200 characters)", 200, 1);
		
		   // Add exercises to the workout
	    ArrayList<Integer> listOfEquipmentIDs = EquipmentManager.GetListOfEquipmentIDs();
	    
	    int equipmentID = 0;
	    
	    boolean addEquipment = false;
	    
	    // Only offer to add equipment if it exists
	    if (listOfEquipmentIDs.size() > 0)
	    {
	    	// Ask for confirmation
			String confirmation = UserInterfaceValidation.GetConfirmation("Do you want to add equipment to the exercise? ('y' = yes/'n' = no)");
			
			if (UserInterfaceValidation.isConfirmation(confirmation))
			{

			    equipmentID = UserInterfaceValidation.GetIntegerInput
			    		("Enter EquipmentID to add equipment to exercise: (Integer)", listOfEquipmentIDs);
			    addEquipment = true;
				
			}
			else {
				System.out.println("No confirmed\n");
			}
	    }
 
	    ExerciseManager.CreateExercise(name, description, bodypart, addEquipment, equipmentID);
	}
	
	public static void ViewExercises()
	{
		
	}
	
	public static void EditExercise()
	{
		System.out.println("Not in the requirements for the Project, so not implemented yet");
	}
	
	public static void AverageKilosExercise()
	{
		
	}
	
	public static void DeleteExercise()
	{
		System.out.println("Not in the requirements for the Project, so not implemented yet");
	}
	
	public static void CreateEquipment()
	{

	    String name = UserInterfaceValidation.GetStringInput("Enter name: (Max 40 characters)", 40);
	    
	    String description = UserInterfaceValidation.GetStringInput("Enter description: (Max 200 characters)", 200);
	    
	    EquipmentManager.CreateEquipment(name, description);
	}
	
	public static void ViewEquipment()
	{
		EquipmentManager.PrintAllEquipment();
	}
	
	public static void EditEquipment()
	{
		System.out.println("Not in the requirements for the Project, so not implemented yet");
	}
	
	public static void DeleteEquipment()
	{
		System.out.println("Not in the requirements for the Project, so not implemented yet");
	}
	
	public static void CreateGroupOfExercises()
	{
		
	}
	
	public static void ViewGroupOfExercises()
	{
		
	}
	
	public static void EditGroupOfExercises()
	{
		System.out.println("Not in the requirements for the Project, so not implemented yet");
	
	}
	
	public static void DeleteGroupOfExercises()
	{
		System.out.println("Not in the requirements for the Project, so not implemented yet");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
