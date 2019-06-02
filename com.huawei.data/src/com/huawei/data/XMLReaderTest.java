package com.huawei.data;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSInput;
import org.xml.sax.SAXException;

/**
 * 
  * @类描述：
 * @项目名称：checkList
  * @包名： com.dcits.checklist
  * @类名称：XMLReaderTest
  * @创建人：zhangpfg
  * @创建时间：2019年5月30日下午5:05:44
  * @修改人：zhangpfg
  * @修改时间：2019年5月30日下午5:05:44
  * @修改备注：
 * @version v1.0
  * @see 
  * @bug 
  * @Copyright 
  * @mail *@qq.com
 */
public class XMLReaderTest {
	
	private static final String OSGI_CONFIGURATION_AREA = "osgi.configuration.area";
	private static final String JAR_EXTENSION = "jar";
	private static final String PLUGINS_DIR = "plugins";
	private static final String TRANS_DIR = "trans";
	private static final String REFERENCE_FILE = "reference:file:";
	private static final String BUNDLE_INSTALLER = "Bundle-Installer";
	private static final String ENABLE_WEB_BUNDLES = "enableWebBundles";
	private static final String LINE_SEPARATOR = "\r\n";
	//启动第一梯队
//	private static final String[] FIRST_BUNDLES_DIR = new String[]{"common","javax","other","querydsl","blueprint","spring","web","equinox","platform-env","platform","appcommon"};
	private static final String[] FIRST_BUNDLES_DIR = new String[]{"common","app"};
	
	public static void main(String[] args) {
		
		XMLReaderTest xml = new XMLReaderTest();
//		xml.installConfig();
		xml.installPluginsTest(new File("F:\\testdata\\plugins"));
		String str = "org.apache";
		String str1 = "org.apache.commons.codec-1.3.0.v20100518-1140.jar";
		String regx = "^org.apache.commons.codec-.*";

		if(str1.matches(regx)){
			System.out.println("da");
		}
    }
	
	private List<String> initConfigMapping(String filePath) {
		List<String> list = null;
		if (null == filePath && filePath.isEmpty()) {
			return list;
		}
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// File FileDirectory = new
		// File("G:\\javacodes\\T5.2.0.All\\run-time\\standard-build\\SmartTeller_TSmartTellerT9.5.2.0\\plugins");
		// 创建一个DocumentBuilder对象
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			// 通过DocumentBuilder对象的parse(String fileName)方法加载books.xml文件到当前项目下

			Document document = db.parse(filePath);// 相对路径
			// 获取Directory节点的集合
			NodeList direoryctList = document.getElementsByTagName("Directory");
			Node direory = direoryctList.item(0);
			// 解析direory节点的子节点
			NodeList childNodes = direory.getChildNodes();
			list = new ArrayList<String>();
			for (int k = 0; k < childNodes.getLength(); k++) {
				// 控制台输出#text就是空白和换行符
				// System.out.println(childNodes.item(k).getNodeName());

				// 通过if方法区分出text类型node和element类型的node
				if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
					// 获取element类型节点的节点名
					System.out.println(childNodes.item(k).getNodeName());
					// 获取element类型节点的节点值
					System.out.println(childNodes.item(k).getFirstChild().getNodeValue());
					String fileName = childNodes.item(k).getFirstChild().getNodeValue();
					// 下面语句可同样达到上述效果
					// System.out.println(childNodes.item(k).getTextContent());
					// 区别是 假如<name><a>新增加的内容</a>冰与火之歌</name>,后者可以把
					// 所有文本内容都可以获取到
					list.add(fileName);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	private static final FileFilter PLUGIN_FILTER = new FileFilter() {

		@Override
		public boolean accept(File file) {
			if (file != null) {
				if (file.isDirectory()) {
					return true;
				}

				String name = file.getName();
				if (name != null) {
					int lastIndex = name.lastIndexOf('.');
					if (lastIndex > 0) {
						String extension = name.substring(lastIndex + 1);
						return JAR_EXTENSION.equalsIgnoreCase(extension);
					}
				}
			}

			return false;
		}
	};
	private void installConfig(){
		LinkedHashMap<String,List<String>> plugins = new LinkedHashMap<String, List<String>>();
		List<String>  list = null;
        // 创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        File FileDirectory = new File("G:\\javacodes\\T5.2.0.All\\run-time\\standard-build\\SmartTeller_TSmartTellerT9.5.2.0\\plugins");
        //创建一个DocumentBuilder对象
        try {
            DocumentBuilder db=dbf.newDocumentBuilder();
        //通过DocumentBuilder对象的parse(String fileName)方法加载books.xml文件到当前项目下

                Document document=db.parse("config/loadFile.xml");//相对路径
                //获取Directory节点的集合
                NodeList direoryctList=document.getElementsByTagName("Directory");
                //遍历Directorys节点集合
                for(int i=0;i<direoryctList.getLength();i++){
                    System.out.println("========下面开始遍历第"+(i+1)+"目录的内容========");
                    Node direory=direoryctList.item(i);
                    //遍历direory属性
                    NamedNodeMap attrs=direory.getAttributes();//获取direory节点中的所有属性值
                    System.out.println("第"+(i+1)+"文件夹共有"+attrs.getLength()+"种文件");
                    String nodeName = attrs.item(0).getNodeValue();
                    System.out.println("属性值:"+nodeName);
                    
//                    for(int j=0;j<attrs.getLength();j++){
//                        Node attr=attrs.item(j);//获取book节点的某一个属性
//                        //获取属性名
//                        attr.getNodeName();
//                        System.out.println("属性名:"+attr.getNodeName());
//                        //获取属性值
//                        System.out.println("属性值:"+attr.getNodeValue());
//                    }
                    //解析book节点的子节点
                    NodeList childNodes=direory.getChildNodes();
                    //遍历childNodes获取每个节点的节点名和节点值
                    //注意：空白和换行符算一个节点
                    //System.out.println("第"+(i+1)+"本书共有"+childNodes.getLength()+"个节点");
                	list = new ArrayList<String>();
                    for(int k=0;k<childNodes.getLength();k++){
            //控制台输出#text就是空白和换行符 System.out.println(childNodes.item(k).getNodeName());

                
                        //通过if方法区分出text类型node和element类型的node
                        if(childNodes.item(k).getNodeType()==Node.ELEMENT_NODE){
                            //获取element类型节点的节点名
                            System.out.println(childNodes.item(k).getNodeName());
                            //获取element类型节点的节点值
                            System.out.println(childNodes.item(k).getFirstChild().getNodeValue());
                            String fileName = childNodes.item(k).getFirstChild().getNodeValue();
                            //下面语句可同样达到上述效果
                            //System.out.println(childNodes.item(k).getTextContent());
                            //区别是 假如<name><a>新增加的内容</a>冰与火之歌</name>,后者可以把
                            //所有文本内容都可以获取到
                            list.add(fileName);
                        }
                        }
                    System.out.println("========结束遍历第"+(i+1)+"本书的内容========");
                    //添加数据
                    plugins.put(nodeName, list);
                    System.out.println(plugins);
                }
        }
         catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         catch (SAXException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        installPlugins(FileDirectory,plugins);
	}
	private void installPluginsTest( File pluginsDirectory) {	
//		String str = bundleContext.getProperty(ENABLE_WEB_BUNDLES);
//		if (str == null) {
//			str = System.getProperty(ENABLE_WEB_BUNDLES, String.valueOf(false));
//		}
		//启动第一梯队bundle
		LinkedHashMap<String,List<File>> plugins = new LinkedHashMap<String, List<File>>();
		String xmlFileName = "";
		StringBuilder sb = null;
		for (String path : FIRST_BUNDLES_DIR) {
			List<File> _lt = new ArrayList<File>();
			listFiles(_lt, new File(pluginsDirectory, path));
			xmlFileName = pluginsDirectory.getAbsolutePath() + path + ".xml";
			sb = new StringBuilder();
			sb.append(pluginsDirectory.getAbsolutePath()).append(File.separator).append(path).append(File.separator)
					.append(path).append(".xml");

			// 进行排序
			// _lt.set(0, arg1)
			List<File> lt = listSort(_lt, initConfigMapping(sb.toString()));
			plugins.put(path, lt);
		}
		System.out.println("final plugins:" + plugins);
	}
	private void installPlugins( File pluginsDirectory,LinkedHashMap<String,List<String>> pluginsFlag) {	
//		String str = bundleContext.getProperty(ENABLE_WEB_BUNDLES);
//		if (str == null) {
//			str = System.getProperty(ENABLE_WEB_BUNDLES, String.valueOf(false));
//		}
		//启动第一梯队bundle
		LinkedHashMap<String,List<File>> plugins = new LinkedHashMap<String, List<File>>();
		for(String path : FIRST_BUNDLES_DIR){
			List<File> _lt = new ArrayList<File>();
			listFiles(_lt, new File(pluginsDirectory,path));
			
			//进行排序
//			_lt.set(0, arg1)
			List<File>  lt = listSort(_lt,pluginsFlag.get(path));
			plugins.put(path, lt);
		}
		System.out.println("final plugins:"+plugins);
//		doInstallPlugins(bundleContext, plugins);
		//启动第二梯队
//		List<File> s_plugins = new ArrayList<File>();
//		for(String path : SECOND_BUNDLES_DIR){
//			listFiles(s_plugins, new File(pluginsDirectory,path));
//		}
//		doInstallPlugins(bundleContext, s_plugins);
//		//启动第三梯队
//		List<File> t_plugins = new ArrayList<File>();
//		for(String path : THREAD_BUNDLES_DIR){
//			listFiles(t_plugins, new File(pluginsDirectory,path));
//		}
//		doInstallPlugins(bundleContext, t_plugins);
		
	}
	
	private List<File>  listSort (List<File> _lt,List<String> listStr){
		
		if(null == _lt ||_lt.isEmpty()){
			return null;
		}
		List<File> list = new ArrayList<File>();
		//配置文件与对应目录下的长度保持一致
String fileName = "";
//		File file = null;
		for(String regx : listStr){
			for(File file : _lt){
//				System.out.println("file.getName():" + file.getName() +",regx:" + regx);
				fileName = file.getName();
				
				if(fileName.matches(regx)){
					System.out.println("file.getName()"+ file.getName());
					list.add(file);
					break;
				}
//				if(isMatch(fileName, regx)){
//					list.add(file);
//					break;
//				}
				
			}
		}
		
		return list;
	}
	
	private boolean isMatch(String fileName, String regx){
		
		Pattern pattern = Pattern.compile(regx);


		    Matcher matcher = pattern.matcher(fileName);
		    boolean isMatch = matcher.find();
		    return isMatch;
		
	}
	private static void listFiles(List<File> files, File directory, File... excludeDirectories) {
		File[] found = directory.listFiles(PLUGIN_FILTER);
		if (found != null) {
			for (File file : found) {
				if (file.isDirectory()) {
					boolean exclude = false;
					for (File excludeDirectory : excludeDirectories) {
						if (file.equals(excludeDirectory)) {
							exclude = true;
						}
					}

					if (exclude) {
						continue;
					} else {
						listFiles(files, file, excludeDirectories);
					}
				} else {
					
					files.add(file);
				}
			}
		}
	}

}


