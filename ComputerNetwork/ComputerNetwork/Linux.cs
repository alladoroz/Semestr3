using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ComputerNetwork
{
    /// <summary>
    /// Linux OS
    /// </summary>
    public class Linux : OperationSystem
    {
        /// <summary>
        /// Linux' probability to get infected
        /// </summary>
        /// <returns></returns>
        protected override int GetProbability()
        {
            return 33;
        }
    }
}
