package cn.BX.filter;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * ��GET����������Դ���
 * @author qdmmy6
 *
 */
public class GetRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	private String charset;
	
	public GetRequest(HttpServletRequest request, String charset) {
		super(request);
		this.request = request;
		this.charset = charset;
	}

	@Override
	public String getParameter(String name) {
		// ��ȡ����
		String value = request.getParameter(name);
		if(value == null) return null;//���Ϊnull��ֱ�ӷ���null
		try {
			// �Բ������б��봦��󷵻�
			return new String(value.getBytes("ISO-8859-1"), charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map getParameterMap() {
		Map<String,String[]> map = request.getParameterMap();
		if(map == null) return map;
		// ����map����ÿ��ֵ���б��봦��
		for(String key : map.keySet()) {
			String[] values = map.get(key);
			for(int i = 0; i < values.length; i++) {
				try {
					values[i] = new String(values[i].getBytes("ISO-8859-1"), charset);
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
		}
		// ����󷵻�
		return map;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		for(int i = 0; i < values.length; i++) {
			try {
				values[i] = new String(values[i].getBytes("ISO-8859-1"), charset);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return values;
	}
}
