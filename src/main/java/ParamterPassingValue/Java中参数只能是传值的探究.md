#### 当一个对象被当作参数传递到一个方法后，在此方法内可以改变这个对象的属性，那么这里到底是值传递还是引用传递?

答：是值传递。Java 语言的参数传递只有值传递。当一个实例对象作为参数被传递到方法中时，
参数的值就是该对象的引用的一个副本。指向同一个对象，对象的内容可以在被调用的方法内改变，
但对象的引用(不是引用的副本) 是永远不会改变的。
Java的参数传递，不管是基本数据类型还是引用类型的参数，都是按值传递，没有按引用传递！

参数如果是引用数据类型，则传递给参数的也是值，只不过这个值是一个地址(可以理解为间接引用)
参数名称在局部变量表中新得到一个位置来储存传递过来的这个对象的地址信息，相当于局部变量表
中有两个不同的名称的变量中存储了一个相同的位置信息，如果直接使用这两个变量就会直接找到堆中
一个相同的对象，但是如果对任意一个变量重新赋值的话，仅仅是将该变量的存储的地址改变为其他，
不会对堆中变量造成影响。