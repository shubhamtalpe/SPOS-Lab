Completed

compile the .java file using following command
    javac file_name.java -h <folder_name>
    
create a .cpp file and write definitions for all function declarations in file_name.h file

compile the gcc file using
    g++ -I/path1 -I/path2 -shared -o file_name.so file_name.cpp
    where path1 is path of jni.h (typically in /usr/lib/jvm/default-jdk/include)
    where path2 is path of jni_md.h (typically in /usr/lib/jvm/default-jdk/include/linux)
    
Set LD_LIBRARY_PATH variable using
    export LD_LIBRARY_PATH="pwd"
    where pwd is path of file_name.so file
    
Execute java class file using
    java file_name
