using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ComputerNetwork
{
    /// <summary>
    /// Mac OS
    /// </summary>
    public class Mac : OperationSystem
    {
        /// <summary>
        /// Mac'probability to get infected
        /// </summary>
        /// <returns></returns>
        protected override int GetProbability()
        {
            return 40;
        }
    }
}
