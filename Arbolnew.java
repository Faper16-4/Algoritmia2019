
  
class Node  
{  
    int key, Value;  
    Node izquierda, derecha;  
  
    Node(int d)  
    {  
        key = d;  
        Value = 1;  
    }  
}  
  
class Arbol  
{  
    Node root;  
  
    
    int height(Node N)  
    {  
        if (N == null)  
            return 0;  
        return N.Value;  
    }  
  
 
    int max(int a, int b)  
    {  
        return (a > b) ? a : b;  
    }  
  
     
    Node rightRotate(Node y)  
    {  
        Node x = y.izquierda;  
        Node T2 = x.derecha;  
  
         
        x.derecha = y;  
        y.izquierda = T2;  
  
          
        y.Value = max(height(y.izquierda), height(y.derecha)) + 1;  
        x.Value = max(height(x.izquierda), height(x.derecha)) + 1;  
  
         
        return x;  
    }  
  
     
    Node leftRotate(Node x)  
    {  
        Node y = x.derecha;  
        Node T2 = y.izquierda;  
  
          
        y.izquierda = x;  
        x.derecha = T2;  
  
         
        x.Value = max(height(x.izquierda), height(x.derecha)) + 1;  
        y.Value = max(height(y.izquierda), height(y.derecha)) + 1;  
  
        
        return y;  
    }  
  
    
    int getBalance(Node N)  
    {  
        if (N == null)  
            return 0;  
        return height(N.izquierda) - height(N.derecha);  
    }  
  
    Node insertar(Node node, int key)  
    {  
        
        if (node == null)  
            return (new Node(key));  
  
        if (key < node.key)  
            node.izquierda = insertar(node.izquierda, key);  
        else if (key > node.key)  
            node.derecha = insertar(node.derecha, key);  
        else  
            return node;  
  
        
        node.Value = 1 + max(height(node.izquierda),  
                            height(node.derecha));  
  
        
        int balance = getBalance(node);  
  
         
        if (balance > 1 && key < node.izquierda.key)  
            return rightRotate(node);  
  
         
        if (balance < -1 && key > node.derecha.key)  
            return leftRotate(node);  
  
          
        if (balance > 1 && key > node.izquierda.key)  
        {  
            node.izquierda = leftRotate(node.izquierda);  
            return rightRotate(node);  
        }  
  
         
        if (balance < -1 && key < node.derecha.key)  
        {  
            node.derecha = rightRotate(node.derecha);  
            return leftRotate(node);  
        }  
  
       
        return node;  
    }  
  
   
    Node minValueNode(Node node)  
    {  
        Node current = node;  
  
      
        while (current.izquierda != null)  
        current = current.izquierda;  
  
        return current;  
    }  
  
    Node Borrar(Node root, int key)  
    {  
          
        if (root == null)  
            return root;  
  
          
        if (key < root.key)  
            root.izquierda = Borrar(root.izquierda, key);  
  
         
        else if (key > root.key)  
            root.derecha = Borrar(root.derecha, key);  
  
         
        else
        {  
  
             
            if ((root.izquierda == null) || (root.derecha == null))  
            {  
                Node temp = null;  
                if (temp == root.izquierda)  
                    temp = root.derecha;  
                else
                    temp = root.izquierda;  
  
                // No child case  
                if (temp == null)  
                {  
                    temp = root;  
                    root = null;  
                }  
                else // One child case  
                    root = temp; 
            }  
            else
            {  
  
                 
                Node temp = minValueNode(root.derecha);  
  
                 
                root.key = temp.key;  
  
                
                root.derecha = Borrar(root.derecha, temp.key);  
            }  
        }  
  
         
        if (root == null)  
            return root;  
  
          
        root.Value = max(height(root.izquierda), height(root.derecha)) + 1;  
  
       
        int balance = getBalance(root);  
  
       
        if (balance > 1 && getBalance(root.izquierda) >= 0)  
            return rightRotate(root);  
  
         
        if (balance > 1 && getBalance(root.izquierda) < 0)  
        {  
            root.izquierda = leftRotate(root.izquierda);  
            return rightRotate(root);  
        }  
  
         
        if (balance < -1 && getBalance(root.derecha) <= 0)  
            return leftRotate(root);  
  
         
        if (balance < -1 && getBalance(root.derecha) > 0)  
        {  
            root.derecha = rightRotate(root.derecha);  
            return leftRotate(root);  
        }  
  
        return root;  
    }  
  
    
    void preOrder(Node node)  
    {  
        if (node != null)  
        {  
            System.out.print(node.key + " ");  
            preOrder(node.izquierda);  
            preOrder(node.derecha);  
        }  
    }  
  
    public static void main(String[] args)  
    {  
        Arbol tree = new Arbol();  
  
      
        tree.root = tree.insertar(tree.root, 9);  
        tree.root = tree.insertar(tree.root, 5);  
        tree.root = tree.insertar(tree.root, 10);  
        tree.root = tree.insertar(tree.root, 0);  
        tree.root = tree.insertar(tree.root, 6);  
        tree.root = tree.insertar(tree.root, 11);  
        tree.root = tree.insertar(tree.root, -1);  
        tree.root = tree.insertar(tree.root, 1);  
        tree.root = tree.insertar(tree.root, 2);  
  
    
        System.out.println("Recorrido "+  
                            "Arbol contruido: ");  
        tree.preOrder(tree.root);  
  
        tree.root = tree.Borrar(tree.root, 10);  
  
      
        System.out.println("");  
        System.out.println("recorrido preorden "+  
                        "borrar 10 :");  
        tree.preOrder(tree.root);  
    }  
}  
  
