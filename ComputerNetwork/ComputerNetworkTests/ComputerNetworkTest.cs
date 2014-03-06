using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using ComputerNetwork;

namespace ComputerNetworkTests
{
    [TestClass]
    public class ComputerNetworkTest
    {
        [TestMethod]
        public void TestInfection()
        {
            byte[,] connections = 
            {
                {0, 0, 0, 1, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 1, 0, 0}
            };

            Computer[] users = new Computer[9];

            users[0] = new Computer(new Windows()) { IsInfected = true };
            users[1] = new Computer(new Windows()) { IsInfected = false };
            users[2] = new Computer(new Linux()) { IsInfected = false };
            users[3] = new Computer(new Linux()) { IsInfected = false };
            users[4] = new Computer(new Mac()) { IsInfected = false };
            users[5] = new Computer(new Mac()) { IsInfected = false };
            users[6] = new Computer(new Windows()) { IsInfected = true };
            users[7] = new Computer(new Windows()) { IsInfected = false };
            users[8] = new Computer(new Windows()) { IsInfected = true };

            ComputerNetwork.ComputerNetwork  network = new ComputerNetwork.ComputerNetwork(connections, users);
   
            for (int i = 0; i < 100; ++i)
            {
                network.Update(connections);
            }
            Assert.AreEqual("66% infected", network.Print());
        }

        [TestMethod]
        public void TestBFS()
        {
            byte[,] connections = 
            {
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 1, 0}
            };

            Computer[] users = new Computer[9];

            users[0] = new Computer(new TestOS()) { IsInfected = true };
            users[1] = new Computer(new TestOS()) { IsInfected = false };
            users[2] = new Computer(new TestOS()) { IsInfected = false };
            users[3] = new Computer(new TestOS()) { IsInfected = false };
            users[4] = new Computer(new TestOS()) { IsInfected = false };
            users[5] = new Computer(new TestOS()) { IsInfected = false };
            users[6] = new Computer(new TestOS()) { IsInfected = false };
            users[7] = new Computer(new TestOS()) { IsInfected = false };
            users[8] = new Computer(new TestOS()) { IsInfected = false };

            ComputerNetwork.ComputerNetwork network = new ComputerNetwork.ComputerNetwork(connections, users);
            Assert.AreEqual(network.Update(connections), network.BFS(0));
            
        }

        [TestMethod]
        public void TestBFS2()
        {
            byte[,] connections = 
            {
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
            };

            Computer[] users = new Computer[4];

            users[0] = new Computer(new TestOS()) { IsInfected = true };
            users[1] = new Computer(new TestOS()) { IsInfected = false };
            users[2] = new Computer(new TestOS()) { IsInfected = false };
            users[3] = new Computer(new TestOS()) { IsInfected = false };


            ComputerNetwork.ComputerNetwork network = new ComputerNetwork.ComputerNetwork(connections, users);
            Assert.AreEqual(network.Update(connections), network.BFS(0));
        }

        private class TestOS : OperationSystem
        {
            protected override int GetProbability()
            {
                return 100;
            }
        }
    }
}
