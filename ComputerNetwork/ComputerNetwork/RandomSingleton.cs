using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ComputerNetwork
{
    public class RandomSingleton
    {
        private static Random instance;

        private RandomSingleton() { }

        public static Random Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new Random(100);
                }
                return instance;
            }
        }
    }
}
