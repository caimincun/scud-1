package cn.scud.utils;


import java.io.InputStream;

/**
 * Created by Administrator on 2015/6/14.
 *  获取前台的输入流，转换为 实体对象
 */
public  class StreamSerializer {


    public static <T> T streamSerializer(InputStream stream,Class<?> cls) throws Exception{
        byte[] bytes = new byte[1024 * 1024];
        InputStream is =  stream;
        int nRead = 1;
        int nTotalRead = 0;
        while (nRead > 0) {
            nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
            if (nRead > 0)
                nTotalRead = nTotalRead + nRead;
        }
        String str = new String(bytes, 0, nTotalRead, "utf-8");

        JsonSerializer jsonSerializer = new JsonSerializer();
        return jsonSerializer.deSerialize(str,cls);

    }
}
