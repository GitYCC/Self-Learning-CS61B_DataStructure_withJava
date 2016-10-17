/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

	/**
	 * Define any variables associated with a RunLengthEncoding object here.
	 * These variables MUST be private.
	 */
	private int width, height;
	private DoublyLinkedNode<Elem> head;

	/**
	 * The following methods are required for Part II.
	 */

	/**
	 * RunLengthEncoding() (with two parameters) constructs a run-length
	 * encoding of a black PixImage of the specified width and height, in which
	 * every pixel has red, green, and blue intensities of zero.
	 *
	 * @param width
	 *            the width of the image.
	 * @param height
	 *            the height of the image.
	 */
	public static class Elem {
		private short red;
		private short green;
		private short blue;
		private int runLengths;
		public Elem (short r,short g,short b,int runLengths){
			this.red = r;
			this.green = g;
			this.blue = b;
			this.runLengths = runLengths;
		}
		public void set (short r,short g,short b,int runLengths) {
			this.red = r;
			this.green = g;
			this.blue = b;
			this.runLengths = runLengths;		
		}
		public void setRunLengths(int runLengths) {this.runLengths=runLengths;}
		public short getRed () { return this.red; }
		public short getGreen () { return this.green; }
		public short getBlue () { return this.blue; }
		public int getRunLengths () { return this.runLengths; }
	}
	
	public RunLengthEncoding(int width, int height) {
		this.width = width;
		this.height = height;
		// using sentinel method
		this.head = new DoublyLinkedNode<Elem>(null,null,null);
		Elem first_item = new Elem((short) 0,(short) 0,(short) 0,width*height);
		DoublyLinkedNode<Elem> first_nod = new DoublyLinkedNode<Elem>(first_item,this.head,this.head);
		this.head.setNext(first_nod);
		this.head.setPrev(first_nod);
	}

	/**
	 * RunLengthEncoding() (with six parameters) constructs a run-length
	 * encoding of a PixImage of the specified width and height. The runs of the
	 * run-length encoding are taken from four input arrays of equal length. Run
	 * i has length runLengths[i] and RGB intensities red[i], green[i], and
	 * blue[i].
	 *
	 * @param width
	 *            the width of the image.
	 * @param height
	 *            the height of the image.
	 * @param red
	 *            is an array that specifies the red intensity of each run.
	 * @param green
	 *            is an array that specifies the green intensity of each run.
	 * @param blue
	 *            is an array that specifies the blue intensity of each run.
	 * @param runLengths
	 *            is an array that specifies the length of each run.
	 *
	 *            NOTE: All four input arrays should have the same length (not
	 *            zero). All pixel intensities in the first three arrays should
	 *            be in the range 0...255. The sum of all the elements of the
	 *            runLengths array should be width * height. (Feel free to quit
	 *            with an error message if any of these conditions are not
	 *            met--though we won't be testing that.)
	 */

	public RunLengthEncoding(int width, int height, int[] red, int[] green, int[] blue, int[] runLengths) {
		this.width = width;
		this.height = height;
		// using sentinel method
		this.head = new DoublyLinkedNode<Elem>(null,null,null);
		this.head.setNext(this.head);
		this.head.setPrev(this.head);
		
		Elem item;
		for (int i=0; i<runLengths.length; i++) {
			item = new Elem((short) red[i],(short) green[i],(short) blue[i],runLengths[i]);
			DoublyLinkedNode<Elem> node = new DoublyLinkedNode<Elem> (item,this.head.getPrev(),this.head);
			this.head.getPrev().setNext(node);
			this.head.setPrev(node);
		}

	}

	/**
	 * getWidth() returns the width of the image that this run-length encoding
	 * represents.
	 *
	 * @return the width of the image that this run-length encoding represents.
	 */

	public int getWidth() {
		return this.width;
	}

	/**
	 * getHeight() returns the height of the image that this run-length encoding
	 * represents.
	 *
	 * @return the height of the image that this run-length encoding represents.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * iterator() returns a newly created RunIterator that can iterate through
	 * the runs of this RunLengthEncoding.
	 *
	 * @return a newly created RunIterator object set to the first run of this
	 *         RunLengthEncoding.
	 */
	public RunIterator iterator() {
		RunIterator runIterator = new RunIterator(this.head);
		return runIterator;
	}

	/**
	 * toPixImage() converts a run-length encoding of an image into a PixImage
	 * object.
	 *
	 * @return the PixImage that this RunLengthEncoding encodes.
	 */
	public PixImage toPixImage() {
		PixImage output = new PixImage(this.width,this.height);
		DoublyLinkedNode<Elem> pointer = this.head.getNext();
		Elem elem;
		int x=0,y=0;
		while (pointer!=this.head) {
			elem = pointer.getElement();
			for (int i=0; i<elem.getRunLengths(); i++) {
				output.setPixel(x, y, elem.getRed(), elem.getGreen(), elem.getBlue());
				x += 1;
				if (x>=this.width) {
					x = 0;
					y += 1;
				}
			}
			pointer = pointer.getNext();
		}
		return output;
	}

	/**
	 * toString() returns a String representation of this RunLengthEncoding.
	 *
	 * This method isn't required, but it should be very useful to you when
	 * you're debugging your code. It's up to you how you represent a
	 * RunLengthEncoding as a String.
	 *
	 * @return a String representation of this RunLengthEncoding.
	 */
	public String toString() {
		DoublyLinkedNode<Elem> pointer = this.head.getNext();
		Elem elem;
		String output = "";
		while (pointer!=this.head) {
			elem = pointer.getElement();
			System.out.println(elem.getRunLengths()+"x[red="+elem.getRed()+",green="+elem.getGreen()+",blue="+elem.getBlue()+"]");
			pointer = pointer.getNext();
		}
		return output;
	}

	/**
	 * The following methods are required for Part III.
	 */

	/**
	 * RunLengthEncoding() (with one parameter) is a constructor that creates a
	 * run-length encoding of a specified PixImage.
	 * 
	 * Note that you must encode the image in row-major format, i.e., the second
	 * pixel should be (1, 0) and not (0, 1).
	 *
	 * @param image
	 *            is the PixImage to run-length encode.
	 */
	public RunLengthEncoding(PixImage image) {
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		// using sentinel method
		this.head = new DoublyLinkedNode<Elem>(null,null,null);
		this.head.setNext(this.head);
		this.head.setPrev(this.head);
		
		short[] temp = new short[3];
		temp[0] = image.getRed(0, 0);
		temp[1] = image.getGreen(0, 0);
		temp[2] = image.getBlue(0, 0);
		int count = 0;
		Elem item = null;
		for (int y=0; y<this.height; y++) {
			for (int x=0; x<this.width; x++) {
				if (image.getRed(x,y)==temp[0] && image.getGreen(x,y)==temp[1] && image.getBlue(x,y)==temp[2]) {
					count++;
					if (x==this.width-1 && y==this.height-1) {
						item = new Elem((short) temp[0],(short) temp[1],(short) temp[2], count);
						DoublyLinkedNode<Elem> node = new DoublyLinkedNode<Elem> (item,this.head.getPrev(),this.head);
						this.head.getPrev().setNext(node);
						this.head.setPrev(node);
					}
				} else {
					item = new Elem((short) temp[0],(short) temp[1],(short) temp[2], count);
					DoublyLinkedNode<Elem> node = new DoublyLinkedNode<Elem> (item,this.head.getPrev(),this.head);
					this.head.getPrev().setNext(node);
					this.head.setPrev(node);
					temp[0] = image.getRed(x, y);
					temp[1] = image.getGreen(x, y);
					temp[2] = image.getBlue(x, y);
					count = 1;
					if (x==this.width-1 && y==this.height-1) {
						item = new Elem((short) temp[0],(short) temp[1],(short) temp[2], 1);
						DoublyLinkedNode<Elem> node2 = new DoublyLinkedNode<Elem> (item,this.head.getPrev(),this.head);
						this.head.getPrev().setNext(node2);
						this.head.setPrev(node2);
					}
				}
			}
		}
		
		check();
	}

	/**
	 * check() walks through the run-length encoding and prints an error message
	 * if two consecutive runs have the same RGB intensities, or if the sum of
	 * all run lengths does not equal the number of pixels in the image.
	 */
	public void check() {
		boolean isSameBetweenConsecutiveRun = false;
		boolean isNotEqualPixels=false;
		int sum_length = 0;
		Elem elem = null;
		Elem temp = null;
		
		DoublyLinkedNode<Elem> pointer = this.head;
		while (pointer.getNext()!=this.head) {
			elem = pointer.getNext().getElement();
			sum_length += elem.getRunLengths();
			if (temp!=null) {
				isSameBetweenConsecutiveRun = !(elem.getRed()==temp.getRed() && elem.getGreen()==temp.getGreen() && elem.getBlue()==temp.getBlue());
			}
			pointer = pointer.getNext();
		}
		isNotEqualPixels = !(sum_length==this.width*this.height);
		
		if ( isSameBetweenConsecutiveRun && isNotEqualPixels) {
			System.out.println("Error: two consecutive runs have the same RGB intensities\nError: sum of all run lengths does not equal the number of pixels in the image.");
			System.exit(1);
		} else if ( isSameBetweenConsecutiveRun ) {
			System.out.println("Error: two consecutive runs have the same RGB intensities");
			System.exit(1);
		} else if (isNotEqualPixels) {
			System.out.println("Error: sum of all run lengths does not equal the number of pixels in the image.");
			System.exit(1);
		} 
	}

	/**
	 * The following method is required for Part IV.
	 */

	/**
	 * setPixel() modifies this run-length encoding so that the specified color
	 * is stored at the given (x, y) coordinates. The old pixel value at that
	 * coordinate should be overwritten and all others should remain the same.
	 * The updated run-length encoding should be compressed as much as possible;
	 * there should not be two consecutive runs with exactly the same RGB color.
	 *
	 * @param x
	 *            the x-coordinate of the pixel to modify.
	 * @param y
	 *            the y-coordinate of the pixel to modify.
	 * @param red
	 *            the new red intensity to store at coordinate (x, y).
	 * @param green
	 *            the new green intensity to store at coordinate (x, y).
	 * @param blue
	 *            the new blue intensity to store at coordinate (x, y).
	 */
	public void setPixel(int x, int y, short red, short green, short blue) {
		// find which node contain this position
		DoublyLinkedNode<Elem> pointer = this.head.getNext();
		Elem elem = null;
		int position_new = y*width+x;
		int first_position_next_set = 0;
		int length_this_set = 0;
		while (pointer!=this.head) {
			elem = pointer.getElement();
			length_this_set = elem.getRunLengths();
			first_position_next_set += length_this_set;			
			if (position_new < first_position_next_set) {
				break;
			}
			
			pointer = pointer.getNext();
		}
		// check whether the replaced element only has one
		// check whether have same RGB
		// 1. NO: check position of the new element in this node
		// 	(1).Not Boundary: one node cut to three ones
		//	(2). Boundary: check whether RGB at the other side is equal to new pixel 
		// 2. YES: do not change anymore

		if (elem.getRed()==red && elem.getGreen()==green && elem.getBlue()==blue) {
			return;
		} else if (elem.getRunLengths()==1) {
			// check whether has same RGB at backward and forward set
			boolean isSameAsForward = (pointer.getPrev().getElement()!=null &&
					red==pointer.getPrev().getElement().getRed() &&
					green==pointer.getPrev().getElement().getGreen() &&
					blue==pointer.getPrev().getElement().getBlue() );
			boolean isSameAsBackward = (pointer.getNext().getElement()!=null &&
					red==pointer.getNext().getElement().getRed() &&
					green==pointer.getNext().getElement().getGreen() &&
					blue==pointer.getNext().getElement().getBlue() );
			
			DoublyLinkedNode<Elem> prevOne = pointer.getPrev();
			DoublyLinkedNode<Elem> nextOne = pointer.getNext();
			if (isSameAsForward && isSameAsBackward ) {
				prevOne.getElement().setRunLengths(prevOne.getElement().getRunLengths()+nextOne.getElement().getRunLengths()+1);
				prevOne.setNext(nextOne.getNext());
				nextOne.setPrev(prevOne);
			} else if (isSameAsForward) {
				prevOne.getElement().setRunLengths(prevOne.getElement().getRunLengths()+1);
				prevOne.setNext(nextOne);
				nextOne.setPrev(prevOne);
			} else if (isSameAsBackward) {
				nextOne.getElement().setRunLengths(nextOne.getElement().getRunLengths()+1);
				nextOne.setPrev(prevOne);
				prevOne.setNext(nextOne);				
			} else {
				pointer.getElement().set(red,green,blue,1);				
			}
			
		} else {
			if (position_new == first_position_next_set-1) { // last one in this set
				Elem next_elem = pointer.getNext().getElement();
				if ( next_elem!=null && next_elem.getRed()==red && next_elem.getGreen()==green && next_elem.getBlue()==blue) {
					pointer.getElement().setRunLengths(elem.getRunLengths()-1);
					pointer.getNext().getElement().setRunLengths(next_elem.getRunLengths()+1);
				} else {
					pointer.getElement().setRunLengths(elem.getRunLengths()-1);
					DoublyLinkedNode<Elem> node = new DoublyLinkedNode<Elem>(new Elem(red,green,blue,1),pointer,pointer.getNext());
					pointer.setNext(node);
					node.getNext().setPrev(node);
				}
			} else if (position_new == first_position_next_set-length_this_set) { //first one is this set
				Elem prev_elem = pointer.getPrev().getElement();
				if ( prev_elem!=null && prev_elem.getRed()==red && prev_elem.getGreen()==green && prev_elem.getBlue()==blue) {
					pointer.getElement().setRunLengths(elem.getRunLengths()-1);
					pointer.getPrev().getElement().setRunLengths(prev_elem.getRunLengths()+1);
				} else {
					pointer.getElement().setRunLengths(elem.getRunLengths()-1);
					DoublyLinkedNode<Elem> node = new DoublyLinkedNode<Elem>(new Elem(red,green,blue,1),pointer.getPrev(),pointer);
					pointer.setPrev(node);
					node.getPrev().setNext(node);
				}				
			} else {
				Elem new_elem1 = new Elem(elem.getRed(),elem.getGreen(),elem.getBlue(),position_new-(first_position_next_set-length_this_set));
				Elem new_elem2 = new Elem(red,green,blue,1);
				Elem new_elem3 = new Elem(elem.getRed(),elem.getGreen(),elem.getBlue(),first_position_next_set-1-position_new);
				DoublyLinkedNode<Elem> node1 = new DoublyLinkedNode<Elem>(new_elem1,pointer.getPrev(),null);
				DoublyLinkedNode<Elem> node2 = new DoublyLinkedNode<Elem>(new_elem2,node1,null);
				DoublyLinkedNode<Elem> node3 = new DoublyLinkedNode<Elem>(new_elem3,node2,pointer.getNext());
				node1.setNext(node2);
				node2.setNext(node3);
				pointer.getNext().setPrev(node3);
				pointer.getPrev().setNext(node1);
			}
		}
		check();
	}

	/**
	 * TEST CODE: YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT. You
	 * are welcome to add tests, though. Methods below this point will not be
	 * tested. This is not the autograder, which will be provided separately.
	 */

	/**
	 * doTest() checks whether the condition is true and prints the given error
	 * message if it is not.
	 *
	 * @param b
	 *            the condition to check.
	 * @param msg
	 *            the error message to print if the condition is false.
	 */
	private static void doTest(boolean b, String msg) {
		if (b) {
			System.out.println("Good.");
		} else {
			System.err.println(msg);
		}
	}

	/**
	 * array2PixImage() converts a 2D array of grayscale intensities to a
	 * grayscale PixImage.
	 *
	 * @param pixels
	 *            a 2D array of grayscale intensities in the range 0...255.
	 * @return a new PixImage whose red, green, and blue values are equal to the
	 *         input grayscale intensities.
	 */
	private static PixImage array2PixImage(int[][] pixels) {
		int width = pixels.length;
		int height = pixels[0].length;
		PixImage image = new PixImage(width, height);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y], (short) pixels[x][y]);
			}
		}

		return image;
	}

	/**
	 * setAndCheckRLE() sets the given coordinate in the given run-length
	 * encoding to the given value and then checks whether the resulting
	 * run-length encoding is correct.
	 *
	 * @param rle
	 *            the run-length encoding to modify.
	 * @param x
	 *            the x-coordinate to set.
	 * @param y
	 *            the y-coordinate to set.
	 * @param intensity
	 *            the grayscale intensity to assign to pixel (x, y).
	 */
	private static void setAndCheckRLE(RunLengthEncoding rle, int x, int y, int intensity) {
		rle.setPixel(x, y, (short) intensity, (short) intensity, (short) intensity);
		rle.check();
	}

	/**
	 * main() runs a series of tests of the run-length encoding code.
	 */
	public static void main(String[] args) {
		// Be forwarned that when you write arrays directly in Java as below,
		// each "row" of text is a column of your image--the numbers get
		// transposed.
		PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 } });

		System.out.println("Testing one-parameter RunLengthEncoding constuctor " + "on a 3x3 image.  Input image:");
		System.out.print(image1);
		RunLengthEncoding rle1 = new RunLengthEncoding(image1);
		rle1.check();
		System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
		doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3, "RLE1 has wrong dimensions");

		System.out.println("Testing toPixImage() on a 3x3 encoding.");
		doTest(image1.equals(rle1.toPixImage()), "image1 -> RLE1 -> image does not reconstruct the original image");

		System.out.println("Testing setPixel() on a 3x3 encoding.");
		setAndCheckRLE(rle1, 0, 0, 42);
		image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
		doTest(rle1.toPixImage().equals(image1),
				/*
				 * array2PixImage(new int[][] { { 42, 3, 6 }, { 1, 4, 7 }, { 2,
				 * 5, 8 } })),
				 */
				"Setting RLE1[0][0] = 42 fails.");

		System.out.println("Testing setPixel() on a 3x3 encoding.");
		setAndCheckRLE(rle1, 1, 0, 42);
		image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
		doTest(rle1.toPixImage().equals(image1), "Setting RLE1[1][0] = 42 fails.");

		System.out.println("Testing setPixel() on a 3x3 encoding.");
		setAndCheckRLE(rle1, 0, 1, 2);
		image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
		doTest(rle1.toPixImage().equals(image1), "Setting RLE1[0][1] = 2 fails.");

		System.out.println("Testing setPixel() on a 3x3 encoding.");
		setAndCheckRLE(rle1, 0, 0, 0);
		image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
		doTest(rle1.toPixImage().equals(image1), "Setting RLE1[0][0] = 0 fails.");

		System.out.println("Testing setPixel() on a 3x3 encoding.");
		setAndCheckRLE(rle1, 2, 2, 7);
		image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
		doTest(rle1.toPixImage().equals(image1), "Setting RLE1[2][2] = 7 fails.");

		System.out.println("Testing setPixel() on a 3x3 encoding.");
		setAndCheckRLE(rle1, 2, 2, 42);
		image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
		doTest(rle1.toPixImage().equals(image1), "Setting RLE1[2][2] = 42 fails.");

		System.out.println("Testing setPixel() on a 3x3 encoding.");
		setAndCheckRLE(rle1, 1, 2, 42);
		image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
		doTest(rle1.toPixImage().equals(image1), "Setting RLE1[1][2] = 42 fails.");

		PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 }, { 2, 4, 5 }, { 3, 4, 6 } });

		System.out
				.println("Testing one-parameter RunLengthEncoding constuctor " + "on another 3x3 image.  Input image:");
		System.out.print(image2);
		RunLengthEncoding rle2 = new RunLengthEncoding(image2);
		rle2.check();
		System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
		doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3, "RLE2 has wrong dimensions");

		System.out.println("Testing toPixImage() on a 3x3 encoding.");
		doTest(rle2.toPixImage().equals(image2), "image2 -> RLE2 -> image does not reconstruct the original image");

		System.out.println("Testing setPixel() on a 3x3 encoding.");
		setAndCheckRLE(rle2, 0, 1, 2);
		image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
		doTest(rle2.toPixImage().equals(image2), "Setting RLE2[0][1] = 2 fails.");

		System.out.println("Testing setPixel() on a 3x3 encoding.");
		setAndCheckRLE(rle2, 2, 0, 2);
		image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
		doTest(rle2.toPixImage().equals(image2), "Setting RLE2[2][0] = 2 fails.");

		PixImage image3 = array2PixImage(new int[][] { { 0, 5 }, { 1, 6 }, { 2, 7 }, { 3, 8 }, { 4, 9 } });

		System.out.println("Testing one-parameter RunLengthEncoding constuctor " + "on a 5x2 image.  Input image:");
		System.out.print(image3);
		RunLengthEncoding rle3 = new RunLengthEncoding(image3);
		rle3.check();
		System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
		doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2, "RLE3 has wrong dimensions");

		System.out.println("Testing toPixImage() on a 5x2 encoding.");
		doTest(rle3.toPixImage().equals(image3), "image3 -> RLE3 -> image does not reconstruct the original image");

		System.out.println("Testing setPixel() on a 5x2 encoding.");
		setAndCheckRLE(rle3, 4, 0, 6);
		image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
		doTest(rle3.toPixImage().equals(image3), "Setting RLE3[4][0] = 6 fails.");

		System.out.println("Testing setPixel() on a 5x2 encoding.");
		setAndCheckRLE(rle3, 0, 1, 6);
		image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
		doTest(rle3.toPixImage().equals(image3), "Setting RLE3[0][1] = 6 fails.");

		System.out.println("Testing setPixel() on a 5x2 encoding.");
		setAndCheckRLE(rle3, 0, 0, 1);
		image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
		doTest(rle3.toPixImage().equals(image3), "Setting RLE3[0][0] = 1 fails.");

		PixImage image4 = array2PixImage(new int[][] { { 0, 3 }, { 1, 4 }, { 2, 5 } });

		System.out.println("Testing one-parameter RunLengthEncoding constuctor " + "on a 3x2 image.  Input image:");
		System.out.print(image4);
		RunLengthEncoding rle4 = new RunLengthEncoding(image4);
		rle4.check();
		System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
		doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2, "RLE4 has wrong dimensions");

		System.out.println("Testing toPixImage() on a 3x2 encoding.");
		doTest(rle4.toPixImage().equals(image4), "image4 -> RLE4 -> image does not reconstruct the original image");

		System.out.println("Testing setPixel() on a 3x2 encoding.");
		setAndCheckRLE(rle4, 2, 0, 0);
		image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
		doTest(rle4.toPixImage().equals(image4), "Setting RLE4[2][0] = 0 fails.");

		System.out.println("Testing setPixel() on a 3x2 encoding.");
		setAndCheckRLE(rle4, 1, 0, 0);
		image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
		doTest(rle4.toPixImage().equals(image4), "Setting RLE4[1][0] = 0 fails.");

		System.out.println("Testing setPixel() on a 3x2 encoding.");
		setAndCheckRLE(rle4, 1, 0, 1);
		image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
		doTest(rle4.toPixImage().equals(image4), "Setting RLE4[1][0] = 1 fails.");
	}
}
