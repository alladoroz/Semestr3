using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;

namespace BST
{
    /// <summary>
    /// BST
    /// </summary>
    public class Tree
    {
        /// <summary>
        /// Tree Constructor
        /// </summary>
        public Tree() { }

        /// <summary>
        /// Searching element
        /// </summary>
        /// <param name="value"></param>
        /// <returns></returns>
        public bool Search(int value)
        {
            if (IsEmpty())
                return false;
            else
                return tree.Search(value);
        }

        /// <summary>
        /// Removing element
        /// </summary>
        /// <param name="value"></param>
        public void Remove(int value)
        {
            Delete(value, tree);
        }

        /// <summary>
        /// Adds element
        /// </summary>
        /// <param name="value"></param>
        public void Add(int value)
        {
            if (IsEmpty())
            {
                tree = new TreeElement();
                tree.Value = value;
                tree.Length++;
            }
            else
                tree.Add(value);
        }

        /// <summary>
        /// Checks if tree is empty
        /// </summary>
        /// <returns></returns>
        public bool IsEmpty()
        {
            return tree == null;
        }

        /// <summary>
        /// Iterator
        /// </summary>
        /// <returns></returns>
        public IEnumerator GetEnumerator()
        {
            return tree.GetEnumerator();
        }

        private void Delete(int value, TreeElement element)
        {
            if (element != null)
            {
                while (value != element.Value)
                {
                    if (value > element.Value && element.Right != null)
                        element = element.Right;

                    else if (value < element.Value && element.Left != null)
                        element = element.Left;

                    else
                        break;
                }

                if (value == element.Value)
                {
                    if (element.Left == null && element.Right == null)
                    {
                        if (element.Parent == null)
                            tree = null;
                        else
                        {
                            if (element.Parent.Value < value)
                                element.Parent.Right = null;
                            else
                            {
                                element.Parent.Left = null;
                            }
                        }
                    }

                    else if (element.Left == null && element.Right != null)
                    {
                        if (element.Parent == null)
                            tree = element.Right;
                        else
                        {
                            if (element.Parent.Value < value)
                                element.Parent.Right = element.Right;
                            else
                            {
                                element.Parent.Left = element.Right;
                            }
                        }
                    }

                    else if (element.Left != null && element.Right == null)
                    {
                        if (element.Parent == null)
                            tree = element.Left;
                        else
                        {
                            if (element.Parent.Value < value)
                                element.Parent.Left = element.Left;
                            else
                            {
                                element.Parent.Right = element.Left;
                            }
                        }
                    }

                    else
                    {
                        TreeElement treeElement = new TreeElement(element.Right);
                        while (treeElement.Left != null)
                        {
                            treeElement = treeElement.Left;
                        }

                        element.Value = treeElement.Value;
                        Delete(treeElement.Value, treeElement);
                    }
                }
            }
        }

        /// <summary>
        /// Tree element class 
        /// </summary>
        private class TreeElement
        {
            /// <summary>
            /// Right subtree
            /// </summary>
            public TreeElement Right { get; set; }

            /// <summary>
            /// Left subtree
            /// </summary>
            public TreeElement Left { get; set; }

            /// <summary>
            /// Parent of node
            /// </summary>
            public TreeElement Parent { get; set; }

            /// <summary>
            /// value in tree
            /// </summary>
            public int Value { get; set; }

            /// <summary>
            /// length
            /// </summary>
            public int Length { get; set; }

            /// <summary>
            /// constructor
            /// </summary>
            /// <param name="element"></param>
            public TreeElement(TreeElement element)
            {
                this.Right = element.Right;
                this.Left = element.Left;
                this.Parent = element.Parent;
            }

            /// <summary>
            /// constructor
            /// </summary>
            public TreeElement()
            {
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
            /// Adds element
            /// </summary>
            /// <param name="value"></param>
            public void Add(int value)
            {
                if (Length == 0)
                {
                    Value = value;
                    Length++;
                }
                else
                {
                    if (Value > value)
                    {
                        if (Left == null)
                        {
                            Left = new TreeElement();
                            Left.Value = value;
                            Left.Length++;
                        }
                        else
                            Left.Add(value);
                    }
                    else
                    {
                        if (Right == null)
                        {
                            Right = new TreeElement();
                            Right.Value = value;
                            Right.Length++;
                        }
                        else
                            Right.Add(value);
                    }
                }
                Length++;
            }

            /// <summary>
            /// Searching element
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
                            return Left.Search(value);
                    }
                    else
                    {
                        if (Right == null)
                            return false;
                        else
                            return Right.Search(value);
                    }
                }
            }
        }

        /// <summary>
        /// head
        /// </summary>
        private TreeElement tree;
    }
}
