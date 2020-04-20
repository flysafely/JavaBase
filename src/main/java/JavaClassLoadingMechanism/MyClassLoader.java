package JavaClassLoadingMechanism;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends  ClassLoader{

        private String className;

        //目录
        private String path;

        private final String fileExtension = ".class";

        public MyClassLoader(String classLoadName){
            super(); //将系统类加载器当做该类加载器的父加载器
            this.className = classLoadName;
        }

        public MyClassLoader(ClassLoader parent, String classLoadName){
            super(parent); //显示指定该类加载器的父加载器器
            this.className = classLoadName;
        }

        public void setPath(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return "[" + this.className + "]";
        }

        @Override
        protected Class<?> findClass(String clasName) throws ClassNotFoundException {
            System.out.println("findClass invoked:" + clasName);
            System.out.println("class loader name: " + this.className);
            byte[] data = this.loadClassData(clasName);
            return  this.defineClass(clasName,data, 0, data.length);
        }

        private byte[] loadClassData(String className){
            InputStream is = null;
            byte[] data = null;
            ByteArrayOutputStream baos = null;

            try{
                className = className.replace(".","//");
                //System.out.println("className:" +this.className);
                is = new FileInputStream(new File(this.path + className + this.fileExtension));
                baos = new ByteArrayOutputStream();
                int ch = 0;
                while ( -1 != (ch = is.read())){
                    baos.write(ch);
                }
                data = baos.toByteArray();

            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                try {
                    is.close();
                    baos.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            return  data;

        }

}
