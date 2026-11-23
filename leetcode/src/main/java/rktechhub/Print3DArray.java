package rktechhub;

public class Print3DArray {

    public static void main(String[] args) {
        //3d array of size 2*3*4
        int[][][] array3D = {
                {
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12}

                },
                {
                        {11, 22, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12}

                }
        };

        System.out.print(array3D[0][0][0] + " ");
        System.out.print(array3D[0][0][1] + " ");
        System.out.print(array3D[0][0][2] + " ");
        System.out.print(array3D[0][0][3] + " ");
        System.out.println("\n");
        System.out.print(array3D[0][1][0] + " ");
        System.out.print(array3D[0][1][1] + " ");
        System.out.print(array3D[0][1][2] + " ");
        System.out.print(array3D[0][1][3] + " ");
        System.out.println("\n");
        System.out.print(array3D[1][0][0] + " ");
        System.out.print(array3D[1][0][1] + " ");
        System.out.print(array3D[1][0][2] + " ");
        System.out.print(array3D[1][0][3] + " ");
        System.out.println("\n");
        System.out.print(array3D[1][1][0] + " ");
        System.out.print(array3D[1][1][1] + " ");
        System.out.print(array3D[1][1][2] + " ");
        System.out.print(array3D[1][1][3] + " ");

        //print 3d array using for loop
        System.out.println("---------------------print 3d array using loop---");
        for (int[][] array2d : array3D) {

            for (int[] array1D : array2d) {
                for (int m : array1D) {
                    System.out.print(m + " ");
                }
                System.out.println("\n");
            }

        }
    }
}
