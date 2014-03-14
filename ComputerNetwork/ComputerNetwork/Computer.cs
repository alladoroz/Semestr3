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
        public Computer(OperationSystem operationSystem)
        {
            this.operationSystem = operationSystem;
        }

        /// <summary>
        /// Gets random number
        /// </summary>
        /// <returns></returns>
        private int RandNext()
        {
            return rand.Next(100);
        }

        /// <summary>
        /// Attack computer and tries to infect it
        /// </summary>
        /// <param name="aim"></param>
        public void Attack(Computer aim)
        {
            if (IsInfected && RandNext() < aim.operationSystem.VirusProbability)
                aim.IsInjured = true;
        }

        private Random rand = new Random();
        private OperationSystem operationSystem;
    }
}
