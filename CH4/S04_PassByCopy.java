
public class S04_PassByCopy {
	public static void dummyFunc (int[] list){
		for (int i=0 ; i<list.length ; i++){
			list[i] *= i+2;
		}
	}
	public static void main (String[] arg) throws Exception{
		int[] integer_list = new int[3];
		integer_list[0] = 1;
		integer_list[1] = 1;
		integer_list[2] = 1;
		System.out.printf("Before Function: %d\n", integer_list[0]);
		dummyFunc(integer_list);
		System.out.printf("After Function: %d\n", integer_list[0]);	
		System.out.println("Copy Reference in Object");
	}
}
