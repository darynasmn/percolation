import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

        private static final int TRIALS = 100;
        private static final double CONFIDENCE = 1.96;
        static double[] array;

        public PercolationStats(){
                array = new double[TRIALS];
                for( int experiment = 0;experiment < TRIALS; experiment++ ){
                        Percolation percolation = new Percolation();
                        while(!Percolation.percolates()){
                         percolation.open();
                         percolation.union();
                }
                        double amount = (double)Percolation.openSites/(Percolation.size*Percolation.size);
                         array[experiment] =  amount;
                }

        }
        public double mean() {
                return StdStats.mean(array);
        }
//        public double stddev(){
//                return StdStats.stddev(array);
//        }
        public double stddev(){
                double max;
                double number=0;
                for(int n =0; n< array.length; n++){
                     double  n1 = Math.pow(array[n]-mean(),2);
                     number += n1;
                }
                max =(Math.sqrt(number/(TRIALS-1)));

                return max;
        }
        public double confidenceLo(){
                return mean()-(CONFIDENCE*stddev())/Math.sqrt(TRIALS);
        }
        public double confidenceHi(){
                return mean()+(CONFIDENCE*stddev())/Math.sqrt(TRIALS);
        }

        public static void main(String[] args){
        PercolationStats statistic = new PercolationStats();
                System.out.println("Середнє значення: "+ statistic.mean());
                System.out.println("Відхилення: " + statistic.stddev());
                System.out.println("Інтервал довіри: " + "[ " + statistic.confidenceLo() + " ; " + statistic.confidenceHi() + "  ]");
        }
    }
