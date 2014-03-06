using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NullElementsCounter
{
    public class Counter
    {
        /// <summary>
        /// Method used to count null elements by lambda-expression
        /// </summary>
        /// <param name="array">input array</param>
        /// <returns>number of null elements</returns>
        public static long LambdaCounter(int[] array)
        {
            return array.Count(x => x == 0);
        }
    }
}
