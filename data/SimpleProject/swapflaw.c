/*
swapflaw.c: A flawed function that swaps two integers.

You can find this example in Norman Matloff, Peter Jay Salzman - The Art of Debugging_ with GDB, DDD, and Eclipse-No Starch Press (2008), page 70.

---------------------------
How to debug this code using gdb-mi:

Note: swapflaw.c is put in ~/Desktop/debugger/example

- Step 1. Compile this file to get object file
Command: "gcc -o swapflaw.out swapflaw.c"
, where "swapflaw.out": the object file

- Step 2. Start gdb-mi
Command: "sudo gdb ~/Desktop/debugger/example/swapflaw --interpreter=mi",
where "~/Desktop/debugger/example/swapflaw": the path of object file in step 1,
and "--interpreter": specify to run with gdb-mi

- Step 3. Add break point to main function
Command: "-break-insert main"

Console will display as follows (for illustration):
    "-break-insert main
     ^done,bkpt={number="1",type="breakpoint",disp="keep",enabled="y",addr="0x0000000100000f0f",
     func="main",file="swapflaw.c",fullname="/Users/ducanhnguyen/Desktop/debugger/example/swapflaw.c",
     line="6",thread-groups=["i1"],times="0",original-location="main"}"

- Step 4: Run the program
Command: "-exec-run"

- ...

- Last step: Terminate the debugger
Command: "-gdb-exit"

Console will display as follows (for illustration):
    "^exit"
*/
#include <stdio.h>
void swap(int a, int b);

int main(void){
	int i = 4;
	int j = 6;

	printf("i: %d, j: %d\n", i, j);
	swap(i, j);
 	printf("i: %d, j: %d\n", i, j);

 	return 0;
}

void swap(int a, int b){
	int c = a;
	a = b;
	b = c;
}