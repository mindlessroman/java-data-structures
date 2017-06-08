/*
#2
@author: Hannah Kennedy - @mindlessroman
@due_date: Spr 2017
*/

import java.util.*;

public class StudentCollection implements Cloneable {
    protected static int STUDENT_MAX = 20;
    private static String[] ids;
    private static String[] names;
    private static int size;


    /**
    * Main tests most of the methods.
    *
    * I am aware that the string arrays for the cloned object do not refer to a new instance.
    **/
    public static void main (String[] args) {
        StudentCollection test = new StudentCollection();
        test.put("123", "Barbara");
        test.put("456", "Monty");
        test.put("789", "Alosha");
        test.put("024", "Quentin");
        test.printStudents();
        System.out.println(test.findIndex("789"));
        System.out.println("the size is..." + test.size());
        System.out.print("Who has ID 904? ");
        System.out.println(test.get("904")); //does nothing
        System.out.print("Who has ID 024? ");
        System.out.println(test.get("024")); // should return quentin
        System.out.println("Remove person at 904:");
        test.remove("904");
        System.out.println("remove person at 123");
        test.remove("123");
        System.out.println("What's the next free spot? " + test.size());
        System.out.println("is the array empty? " + test.isEmpty());
        System.out.println("what are the contents of original array?");
        test.printStudents();
        System.out.println("-------------------------------");
        StudentCollection cloneTest = test.clone();
        cloneTest.put("987", "Yelchin");
        test.printStudents();
        System.out.print("=======================\nprinting the clone\n");
        cloneTest.printStudents();
    }


    /**
     * StudentCollection - copy
     *
     * @param String[] copyNames	The String array of names to copy
     * @param String[] copyIds	The String array of ids to copy
     * @return StudentCollection   The cloned Student Collection
     **/
    public StudentCollection () {
        size = 0;
        ids = new String[STUDENT_MAX];
        names = new String[STUDENT_MAX];
    }

    /**
     * StudentCollection - copy
     *
     * @param String[] copyNames	The String array of names to copy
     * @param String[] copyIds	The String array of ids to copy
     * @param int      copySize	The size of the copied object
     * @return StudentCollection   The cloned Student Collection
     * @TODO: Does not create a deep copy of the String arrays unfortunately.
     * As a result, changes to clone will affect the original in memory.
     **/
    public StudentCollection (String[] copyIds, String[] copyNames, int copySize) {
        names = new String[STUDENT_MAX];
        ids = new String[STUDENT_MAX];
        size = 0;
        for (int i = 0; i < copySize; i++) {
            if (copyNames[i] != null) {
                names[i] = new String(copyNames[i]);
                ids[i] = new String(copyIds[i]);
                size++;
            }
        }
    }

    /**
     * findIndex()
     *
     * @param String id
     * @return int   Returns the index of the array names[] whose identification number is id
     * Returns -1 if no student with id
     */

    private static int findIndex (String id) {
        for (int i = 0; i < size(); i++) {
            if (ids[i] == id) {
                return i;
            }
        }
        return -1;
    }


    /**
     * size()
     *
     * @return int   Returns the number of students in the collection
     */
    public static int size () {
        return size;
    }

    /**
     * isEmpty()
     *
     * @return boolean   Returns true if the collection is empty
     * False otherwise
     */
    public static boolean isEmpty () {
        boolean isNull = true;
        for (int i = 0; i < size; i++) {
            if (ids[i] != null || names[i] != null) {
                isNull = false;
            } else {
                isNull = true;
            }
        }
        return isNull;

    }

    /**
     * get()
     *
     * @param String id   The id of the student to retrieve
     * @return String   Returns the name of the student with given id
     * If no student, returns null.
     */
    public static String get (String id) {
        int nameLoc = findIndex(id);
        String name = "";
        if (nameLoc < 0) {
            name = null;
        } else {
            name = names[nameLoc];
        }
        return name;
    }

    /**
     * put()
     *
     * @param String id
     * @param String name
     * @return null   If no student with given id, add student and name
     * String oldName	 If there is a student, change the name and return the old name
     */
    public static String put (String id, String name) {
        int putIndex = findIndex(id);
        String oldName = "";
        //if there's an index >=0, save the old name and update
        if (putIndex >= 0) {
            oldName = names[putIndex];
            names[putIndex] = name;
        }
        //if the index comes back with -1, then no ID is in the Array
        if (putIndex < 0) {
            ids[size] = id;
            names[size] = name;
            size++;
        }
        return oldName;
    }

    /**
     * remove()
     *
     * @param String id   The String id of student to remove
     * @return If there is a student with given id, remove both the id and name
     * if there is no student, returns null
     */
    public static String remove (String id) {
        int removeIndex = findIndex(id);
        if (removeIndex >= 0) {
            size--;
            ids[removeIndex] = ids[size];
            names[removeIndex] = names[size];

        }
        return null;
    }


    /**
     * clone()
     *
     * @return Creates an independent copy of the object that invokes
     *
     * TODO: Make it actually refer to a new collection of String Arrays
     */
    public StudentCollection clone () {
        return new StudentCollection(ids, names, size);
    }


    /**
     * printStudents()
     *
     * @return Prints the names of all the students
     */
    public static void printStudents () {
        for (int i = 0; i < size(); i++) {
            System.out.print("Id: ");
            System.out.print(ids[i]);
            System.out.print(", Name: ");
            System.out.println(names[i]);
        }
    }
}
