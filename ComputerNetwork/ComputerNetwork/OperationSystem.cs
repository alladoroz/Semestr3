using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ComputerNetwork
{
    /// <summary>
    /// OS of computer
    /// </summary>
    public abstract class OperationSystem
    {
        /// <summary>
        /// Prbability go get infected 
        /// </summary>
        /// <returns></returns>
        protected abstract int GetProbability();

        /// <summary>
        /// virus probability to infect
        /// </summary>
        public int VirusProbability
        {
            get
            {
                if (GetProbability() > 100 || GetProbability()  <0)
                    throw new InvalidOperationException();
                return GetProbability();
            }
        }
    }
}
