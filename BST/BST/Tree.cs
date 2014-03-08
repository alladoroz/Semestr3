using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;

namespace BST
{
    public class Tree : IEnumerable
    {
        /// <summary>
        /// Tree constructor
        /// </summary>
        public Tree()
        {
            this.tree = this;
        }

        /// <summary>
        /// Left subtree
        /// </summary>
        public Tree Left { get; private set; }

        /// <summary>
        /// Right subtree
        /// </summary>
        public Tree Right { get; private set; }

        /// <summary>
        /// value in node
        /// </summary>
        public int Value { get; private set; }

        /// <summary>
        /// Builds iterable tree
        /// </summary>
        /// <returns></returns>
        public IEnumerable<Tree> GetAll()
        {
            if (Left != null)
            {
                foreach (Tree f in Left.GetAll())
                {
                    yield return f;
                }
            }

            yield return this;

            if (Right != null)
            {
                foreach (Tree f in Right.GetAll())
                {
                    yield return f;
                }
            }
        }

        /// <summary>
        /// Iterator
        /// </summary>
        /// <returns></returns>
        public IEnumerator GetEnumerator()
        {
            if (Left != null)
            {
                foreach (int t in Left)
                {
                    yield return t;
                }
            }

            yield return this.Value;

            if (Right != null)
            {
                foreach (int t in Right)
                {
                    yield return t;
                }
            }
        }

        /// <summary>
        /// Prints tree
        /// </summary>
        public void Print()
        {
            foreach (Tree t in GetAll())
            {
                Console.WriteLine(t.Value);
            }
        }

        /// <summary>
        /// Element searching
        /// </summary>
        /// <param name="value"></param>
        /// <returns></returns>
        public bool Search(int value)
        {
            if (Value == value)
                return true;
            else
            {
                if (Value > value)
                {
                    if (Left == null)
                        return false;
                    else
                    {
                        Left.length = length;
                        return Left.Search(value);
                    }
                }
                else
                {
                    if (Right == null)
                        return false;
                    else
                    {
                        Right.length = length;
                        return Right.Search(value);
                    }
                }
            }
        }

        /// <summary>
        /// Deletes element from tree
        /// </summary>
        /// <param name="value"></param>
        public void Delete(int value)
        {
            if (value > Value)
            {
                if (Right != null)
                {
                    if ((Right.Value == value) && (Right.Left == null) && (Right.Right == null))
                    //{
                        this.Right = null;
                       // length--;
                    //}

                    Right.Delete(value);
                }
            }

            if (value < Value)
            {
                if (Left != null)
                {
                    if ((Left.Value == value) && (Left.Left == null) && (Left.Right == null))
                    {
                        Left = null;
                        length--;
                    }

                    Left.Delete(value);
                }
            }

            if (value == Value)
            {
                this.length--;

                if (Right == null && Left == null)
                    return;

                if (Right == null)
                    tree.Left = Left;

                if (Left == null) 
                    tree.Right = Right;

                if (Right.Left != null)
                {
                    Value = Right.Left.Value;
                    Right.Left.Delete(Right.Left.Value);
                }

                else
                {
                    Value = Right.Value;
                    Right.Delete(Right.Value);
                }
            }
        }

        /// <summary>
        /// Element addition
        /// </summary>
        /// <param name="value"></param>
        public void Add(int value)
        {
            if (length == 0)
                Value = value;
            else
            {
                if (Value > value)
                {
                    if (Left == null)
                    {
                        Left = new Tree();
                        Left.Value = value;
                        Left.length++;
                    }
                    else
                    {
                        Left.Add(value);
                    }
                }
                else
                {
                    if (Right == null)
                    {
                        Right = new Tree();
                        Right.Value = value;
                        Right.length++;
                    }
                    else
                    {
                        Right.Add(value);
                    }
                }
            }
            length++;
        }

        private static void Node(Tree tree, bool isLeft)
        {
            if (isLeft)
                tree.Left = null;
            else
                tree.Right = null;
        }

        private Tree tree;
        private int length = 0;
    }
}
