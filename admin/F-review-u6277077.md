Reviewer: Xiao Tian (u6277077)
Component: <...>
Author: Qianru Zhu (u6416655)

Review Comments:

I'm reviewing Cassie's code of a method quick index. This method is to create a coordinate to address each location in the board. 
1. The best feature of it is that the coordinate is easy to understand.
For example, start from the top right of the board, [2][1] means location at the second row and first column,
[5][3] means location at the 5th row and 3rd column. 
Hence when determine if a given move is legal or not, we can quickly check if it is at the legal column or row, without further calculation.
2. The code is well-documented as I understand the code with these comments.
3. The structure of the method is not perform very well. The way it represent A-Z and 0-9 is not conciseness as 2 blocks are written.
4. It follows code conventions and maintain consistent style. But the return statement sometimes confusing.
5. There is no error as far as I reviewed.

1. HelloWorld.java:1-16 Use proper capitalization.
2. HelloWorldTest.java:21 This test will pass with "hello world", when it should fail.
3. <...>