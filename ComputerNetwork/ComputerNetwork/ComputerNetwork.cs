using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Diagnostics;

namespace ComputerNetwork
{
    public class ComputerNetwork
    {

        /// <summary>
        /// Computer constructor
        /// </summary>
        /// <param name="connections"></param>
        /// <param name="users"></param>
        public ComputerNetwork(byte[,] connections, Computer[] users)
        {
            this.connections = connections;
            this.users = users;
        }

        /// <summary>
        /// Trying to infect computer
        /// </summary>
        /// <returns></returns>
        public String Update(byte[,] graph)
        {
            var str = "";
            str = str + Convert.ToString(0);
            for (int i = 0; i < graph.GetLength(0); i++)
                for (int j = i + 1; j < graph.GetLength(0); j++)
                    if (graph[i, j] > 0)
                    {
                        if (!users[j].IsInjured)
                        {
                            users[i].Attack(users[j]);
                            str = str + Convert.ToString(j);
                           // Console.WriteLine(j + "\n");
                        }
                    }

            for (int i = 0; i < graph.GetLength(0); i++)
                if (users[i].IsInjured)
                {
                    users[i].IsInfected = true;
                }

            return str;
        }

        /// <summary>
        /// Prints state
        /// </summary>
        /// <returns></returns>
        public string Print()
        {
            var k = 0;
            for (int i = 0; i < connections.GetLength(0); i++)
                if (users[i].IsInfected)
                    k++;
            Console.WriteLine(k * 100 / connections.GetLength(0) + "% infected");
            return k * 100 / connections.GetLength(0) + "% infected";
        }

        /// <summary>
        /// BFS
        /// </summary>
        /// <param name="peak"
        /// ></param>
        /// <returns></returns>
        public String BFS(int peak)
        {
            var str = "";
            var isVisited = new bool[connections.GetLength(0)];
            var queue = new Queue<int>();

            queue.Enqueue(peak);
            isVisited[peak] = true;
            str = str + Convert.ToString(peak);

            while (queue.Count != 0)
            {
                var current = queue.Dequeue();
                Console.WriteLine(current);
                for (int i = 0; i < connections.GetLength(0); ++i)
                {
                    if (connections[current, i] != 0 && !isVisited[i])
                    {
                        queue.Enqueue(i);
                        isVisited[i] = true;
                        str = str + Convert.ToString(i);
                    }
                }
            }
            return str;
        }

        static void Main(string[] args)
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

            users[0] = new Computer(new Windows()) { IsInfected = true};
            users[1] = new Computer(new Windows()) { IsInfected = false };
            users[2] = new Computer(new Linux()) { IsInfected = false };
            users[3] = new Computer(new Linux()) { IsInfected = false };
            users[4] = new Computer(new Mac()) { IsInfected = false };
            users[5] = new Computer(new Mac()) { IsInfected = false };
            users[6] = new Computer(new Windows()) { IsInfected = false };
            users[7] = new Computer(new Windows()) { IsInfected = false };
            users[8] = new Computer(new Windows()) { IsInfected = true };

            ComputerNetwork network = new ComputerNetwork(connections, users);
            for (int i = 0; i < 100; i++)
            {
                network.Print();
                network.Update(connections);
            }
        }
        
        private Computer[] users;
        private byte[,] connections;
    }
}