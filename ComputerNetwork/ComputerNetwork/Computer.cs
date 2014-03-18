using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ComputerNetwork
{
    /// <summary>
    /// Class used to show if computer can be injured or not
    /// </summary>
    public class Computer
    {
        /// <summary>
        /// shows if computer injured or not
        /// </summary>
        public bool IsInjured { get; set; }
       
        /// <summary>
        /// shows if computer infected or not
        /// </summary>
        public bool IsInfected { get; set; }

        /// <summary>
        /// Computer constructor
        /// </summary>
        /// <param name="operationSystem"></param>
        public Computer(OperationSystem operationSystem, Func<int> random)
        {
            this.operationSystem = operationSystem;
            value = random;
        }

        /// <summary>
        /// Attack computer and tries to infect it
        /// </summary>
        /// <param name="aim"></param>
        public void Attack(Computer aim)
        {
            if (IsInfected &&  value() % 100 < aim.operationSystem.VirusProbability)
                aim.IsInjured = true;
        }

        private OperationSystem operationSystem;
        private Func<int> value;
    }
}

