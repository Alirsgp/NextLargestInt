import java.util.Arrays;


//Given <12234467--------34722641-34724126-34724162-34724216-34724261-34724612---76443221>
//Write the method for returning the next largest value, using only the given permutations in the digits

//Okay, this is an interesting problem. The first task we'll take on will be to take the int given, and
//place it into an array of ints. There's a few ways to go about this, but this is mine:
public class TestFile {

	public static int findNextLargest(int number) {
  
		//We convert our number to a string, and make an array of ints with a length based on the length of that string.
    
		String tempNumber = Integer.toString(number);
    
		int [] myIntArray = new int[tempNumber.length()];
    
		//We then add each character of tempNumber as an int to each index of myIntArray
    
		for (int i = 0; i < myIntArray.length; i++) {
			myIntArray[i] = Integer.parseInt(tempNumber.substring(i, i + 1));
		}
    
		//We create an int i in our method's scope so we can access it later on.
		int i = 0;
    
		//Let's loop through our myIntArray, and decrement i if myIntArray[i] > myIntArray[i - 1]. 
		//We set i to be equal to myIntArray.length - 1 to begin.
		for (i = myIntArray.length - 1; i > 0; i--) {
			if (myIntArray[i] > myIntArray[i - 1]) {
				break;
			}
		}
		//If the elements in myIntArray starting from the very right and moving to the left are increasing,
		//which means we have 76443221 for example, then our i is decremented to 0. Otherwise, it is 
		//decremented until myIntArray[i], which is the digit to the right of myIntArray[i - 1], is greater than
		//myIntArray[i - 1]. Once it is, we break out of our loop, and we do NOT decrement i.
    
		if (i == 0) {
			return number;
		}
		//If our i was fully decremented, meaning we had 76443221 for example, we just return 76443221 as it is the 
		//largest possible value with the current set of permutations of digits given.
		else {
			int myNum = myIntArray[i - 1]; //If we had 34722641, myNum would be 2. It takes the value of the 4th index: 3472'2'641.
			int minimum = i; //If we had 34722641 as input, minimum would be 5. 1 is not greater than 4, 4 is not greater than 6,
			//6 is greater than 2. Minimum is 5.


			//j is equal to 6
			for (int j = i + 1; j < myIntArray.length; j++) {
				if (myIntArray[j] > myNum && myIntArray[j] < myIntArray[minimum]) {
					minimum = j; //minimum on first iteration is now 6.
				}
			}

			//Call our custom swap method. Swap myIntArray's index values at i-1 and minimum.
			swap(myIntArray, i - 1, minimum);

			Arrays.sort(myIntArray, i, myIntArray.length);


			//Now we convert our array of ints into a string, and convert our string to an int and return it.
			String tempAnswer = "";
			for (int k = 0; k < myIntArray.length; k++) {
				tempAnswer += myIntArray[k];
			}
			return Integer.parseInt(tempAnswer);
		}
	}

	public static void swap(int [] myArr, int i, int j) {
		int temp = myArr[i];
		myArr[i] = myArr[j];
		myArr[j] = temp;
	}

	public static void main (String [] args) {
		System.out.println(findNextLargest(34724261));
	}

}
