//package android.hardware.rfid;
package android.hardware.uhf;

import java.util.Arrays;
import java.util.regex.Pattern;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

//import android.util.Log; //mifarereader
public class reader {
	static public Handler m_handler = null;
	static Boolean m_bASYC = false;
	static byte[] m_buf = new byte[10240];
	static int m_nCount = 0;
/**
 * 打开设备
 * @param strpath 设备地址CM719是"//dev//ttyS4"
 * @return 成功返回0，失败返回错误标志（-20产品不对，-1设备无法打开，1设备已打开，
 * -2设备参数无法设置）
 */
	static public native int Open(String strpath);
/**
 * 读数据
 * @param pout		存放读到得数据
 * @param nStart	存放数据在pout的起始位置
 * @param nCount	想读到得数据长度
 * @return
 */
	static public native int Read(byte[] pout, int nStart, int nCount);
/**
 * 关闭设备
 */
	static public native void Close();
/**
 * 清楚设备缓存中数据
 */
	static public native void Clean();
/**
 * 重启设备
 * @param readID 	读写器地址（0-254）255是公用地址
 * @return 			成功返回0x10,错误返回0x11
 */
	static public native int ResetDevice(byte readID);
/**
 * 获得固件版本
 * @param readID 	读写器地址（0-254）255是公用地址
 * @param pout		2个字节，pout[0]固件主版本号，pout[1]固件次版本号
 * @return 			成功返回0x10,错误返回0x11
 */
	static public native int GetFirmwareVersion(byte readID, byte[] pout);
/**
 * 设置读写器地址
 * @param readID	当前读写器地址
 * @param newID		新读写器地址
 * @return			成功返回0x10,错误返回0x11
 */
	static public native int SetReaderAddress(byte readID, byte newID);
/**
 * 设置工作天线，只有一个天线，请使用默认，不要设置
 * @param readID		读写器地址
 * @param WorkAntenna	天线编号（1-4）
 * @return			成功返回0x10,错误返回0x11
 */
	static public native int SetWorkAntenna(byte readID, byte WorkAntenna);
/**
 * 获得当前天线编号
 * @param readID		读写器地址
 * @return				天线编号
 */
	static public native int GetWorkAntenna(byte readID);
/**
 * 设置输出功率
 * @param readID		读写器地址
 * @param outputPower	输出功率（0x14-0x21）
 * @return				成功返回0x10,错误返回0x11
 */
	static public native int SetOutputPower(byte readID, byte outputPower);
/**
 * 获取当前发射功率
 * @param readID		读写器地址
 * @return				失败返回0x11，成功返回发射功率（0x14-0x21）
 */
	static public native int GetOutputPower(byte readID);
/**
 * 获取此频点当前工作天线的回波损耗值
 * @param readID		读写器地址
 * @param Frequency		测试频率
 * @return				失败返回0x11，成功返回回波损耗值（单位db）
 */
	static public native int GetRFReturnLoss(byte readID, byte Frequency);
/**
 * 设置系统默认频点
 * @param readID		读写器地址
 * @param Region		射频规范（0x01 FCC，0x02 EISI，0x03 CHN）
 * @param StartRegion	频率起始点
 * @param EndRegion		频率结束点
 * @return				成功返回0x10,错误返回0x11
 */
	static public native int SetFrequencyRegion(byte readID, byte Region,
			byte StartRegion, byte EndRegion);
/**
 * 自定义设置频点
 * @param readID			读写器地址
 * @param nStartFreq		起始频率（单位KHZ，16进制高位在前，例如：915000KHZ，发送0D F6 38）
 * @param btFreqSpace		频点间隔（btFreqSpace*10KHZ）
 * @param btRreqQuantity	包含起始频率的频点数量，必须大于0
 * @return					成功返回0x10,错误返回0x11
 */
	static public native int SetUserDefineFrequency(byte readID,
			int nStartFreq, byte btFreqSpace, byte btRreqQuantity);

	/**
	 * 获取频率范围
	 * @param readID		读写器地址
	 * @param pout			如果是使用系统默认则返回3个字节（由低到高，射频规范、射频起点、射频结束点），如果自动以5个字节（频率间隔、频点数量、频率起始点（3个字节））
	 * @return				成功返回0x10,错误返回0x11
	 */
	static public native int GetFrequencyRegion(byte readID, byte[] pout);

	static public native int SetBeepMode(byte readID, byte mode);

	static public native int GetReaderTemperature(byte readID, byte[] pout);
/**
 * 设置DRM模式
 * @param readID			读写器地址
 * @param btDrmMode			DRM模式（0关闭DRM，1打开DRM）
 * @return					成功返回0x10,错误返回0x11
 */
	static public native int SetDrmMode(byte readID, byte btDrmMode);
/**
 * 获得DRM模式
 * @param readID			读写器地址
 * @return					返回当前模式（0关闭DRM，1打开DRM，0x11失败）
 */
	static public native int GetDrmMode(byte readID);
/**
 * 获得GPIO值
 * @param readID			读写器地址
 * @param pout				
 * @return
 */
	static public native int ReadGpioValue(byte readID, byte[] pout);

	static public native int WriteGpioValue(byte readID, byte btChooseGpio,
			byte btGpioValue);

	static public native int SetAntDetector(byte readID, byte DetectorStatus);

	static public native int GetImpinjFastTid(byte readID);

	static public native int SetImpinjFastTid(byte readID, byte FastTid);

	static public native int SetRfProfile(byte readID, byte ProfileId);

	static public native int GetRfProfile(byte readID);

	static public native int GetReaderIdentifier(byte readID, byte[] pout);

	static public native int SetReaderIdentifier(byte readID, byte[] pin);

	static public native int Inventory(byte readID, byte btRepeat, byte[] pout);

	static public native int SetAccessEpcMatch(byte readID, byte btMode,
			byte btEpcLen, byte[] pin);

	static public native int GetAccessEpcMatch(byte readID, byte[] pout);

	static public native int GetInventoryBufferTagCount(byte readID);

	static public native int LockTagISO18000(byte readID, byte[] AryUID,
			byte btWordAdd, byte[] pout);

	static public native int QueryTagISO18000(byte readID, byte[] AryUID,
			byte WordAdd, byte[] pout);

	static public native int ReadTag(byte btReadId, byte btMemBank,
			byte btWordAdd, byte btWordCnt, byte[] pPassword);

	static public native int WriteTag(byte btReadId, byte[] AryPassWord,
			byte btMemBank, byte btWordAdd, byte btWordCnt, byte[] jAryData);

	static public native int LockTag(byte btReadId, byte[] pbtAryPassWord,
			byte btMembank, byte btLockType);

	static public native int KillTag(byte btReadId, byte[] pbtAryPassWord);

	static public native int GetInventoryBuffer(byte btReadId);

	static public native int GetAndResetInventoryBuffer(byte btReadId);

	static public native int InventoryReal(byte btReadId, byte byRound);
/**
 * 销毁标签   （结果通过Handle异步发送）
 * @param btReadId			读写器地址
 * @param pbtAryPassWord	销毁密码（4个字节）
 * @return
 */
	static public int KillLables(byte btReadId, byte[] pbtAryPassWord) {
		Clean();
		int nret = KillTag(btReadId, pbtAryPassWord);
		if (!m_bASYC) {
			StartASYCKilllables();
		}
		return nret;
	}
/**
 * 锁定标签 （结果通过Handle异步发送）
 * @param btReadId			读写器地址
 * @param pbtAryPassWord	访问密码（4个字节）
 * @param btMembank			锁定区域（访问密码（4）、销毁密码（5）、EPC（3）、TID（2）、USER（1））
 * @param btLockType		锁定类型（开放（0）、锁定（1）、永久开放（2）、永久锁定（3））
 * @return
 */
	static public int LockLables(byte btReadId, byte[] pbtAryPassWord,
			byte btMembank, byte btLockType) {
		Clean();
		int nret = LockTag(btReadId, pbtAryPassWord, btMembank, btLockType);
		if (!m_bASYC) {
			StartASYCLocklables();
		}
		return nret;
	}
/**
 * 寻卡（结果通过Handle异步发送）
 * @param btReadId			读写器地址
 * @param byRound			存盘过程重复的次数
 * @return
 */
	static public int SearchLables(byte btReadId, byte byRound) {
		Clean();
		int nret = InventoryReal(btReadId, byRound);
		if (!m_bASYC) {
			StartASYClables();
		}
		return nret;    
	}  
/**
 * 读标签（结果通过Handle异步发送，一张卡一条消息）
 * @param btReadId			读写器地址
 * @param btMemBank			标签存储区域（保留（0）、EPC（1）、TID（2）、USER（3））
 * @param btWordAdd			读取数据首地址
 * @param btWordCnt			读取数据长度
 * @param pPassword			标签访问密码（4个字节）
 * @return
 */
	static public int ReadLables(byte btReadId, byte btMemBank, byte btWordAdd,
			byte btWordCnt, byte[] pPassword) {
		int nret = 0;
		if (!m_bASYC) {
			Clean();
			nret = ReadTag(btReadId, btMemBank, btWordAdd, btWordCnt, pPassword);

			StartASYCReadlables();
		}
		return nret;
	}
/**
 * 写标签（结果通过Handle异步发送，一张卡一条消息）
 * @param btReadId			读卡器地址
 * @param AryPassWord		访问密码（4个字节）
 * @param btMemBank			标签存储区域（保留（0）、EPC（1）、TID（2）、USER（3））
 * @param btWordAdd			读取数据首地址
 * @param btWordCnt			写入数据长度
 * @param jAryData			写入的数据
 * @return
 */
	static public int Writelables(byte btReadId, byte[] AryPassWord,
			byte btMemBank, byte btWordAdd, byte btWordCnt, byte[] jAryData) {
		Clean();
		int nret = WriteTag(btReadId, AryPassWord, btMemBank, btWordAdd,
				btWordCnt, jAryData);
		if (!m_bASYC) {
			StartASYCWritelables();
		}
		return nret;
	}

	static void StartASYCKilllables() {
		m_bASYC = true;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int nTemp = 0;
				m_nCount = 0;
				while (m_handler != null) {

					nTemp = Read(m_buf, m_nCount, 1024);
					m_nCount += nTemp;
					if (nTemp == 0)
						break;
					String str=reader.BytesToString(m_buf, 0, m_nCount);
					String[]substr=Pattern.compile("A0(.*?)84").split(str); 
					for(int i=0;i<substr.length;i++)
					{
						if(substr[i].length()>4)
						{
							Message msg = new Message();
							msg.what = (substr[i].length()-14)/2;
							msg.obj = substr[i].substring(6, substr[i].length()-8);
							m_handler.sendMessage(msg);
						}
						else
						{
							Message msg = new Message();
							msg.what = substr[i].length() == 4 ? -1 : 0;
							msg.obj = "";
							m_handler.sendMessage(msg);
						}
					
					}

				}

				m_bASYC = false;
			}
		});
		thread.start();
	}

	static void StartASYCLocklables() {
		m_bASYC = true;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int nTemp = 0;
				m_nCount = 0;
				while (m_handler != null) {

					nTemp = Read(m_buf, m_nCount, 1024);
					m_nCount += nTemp;
					if (nTemp == 0)
						break;
					String str=reader.BytesToString(m_buf, 0, m_nCount);
					String[]substr=Pattern.compile("A0(.*?)83").split(str); 
					for(int i=0;i<substr.length;i++)
					{
						if(substr[i].length()>4)
						{
							Message msg = new Message();
							msg.what = (substr[i].length()-14)/2;
							msg.obj = substr[i].substring(6, substr[i].length()-8);
							m_handler.sendMessage(msg);
						}
						else
						{
							Message msg = new Message(); 
							msg.what = substr[i].length() == 4 ? -1 : 0;
							msg.obj = ""+i;
							m_handler.sendMessage(msg);
						}
					
					}

				}

				m_bASYC = false;
			}
		});
		thread.start();
	}

	static void StartASYCWritelables() {
		m_bASYC = true;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int nTemp = 0;
				m_nCount = 0;
				while (m_handler != null) {

					nTemp = Read(m_buf, m_nCount, 1024);
					m_nCount += nTemp;
					if (nTemp == 0)
						break;
					String str=reader.BytesToString(m_buf, 0, m_nCount);
					String[]substr=Pattern.compile("A0(.*?)82").split(str); 
					for(int i=0;i<substr.length;i++)
					{
						if(substr[i].length()>4)
						{
							Message msg = new Message();
							msg.what = (substr[i].length()-14)/2;
							msg.obj = substr[i].substring(6, substr[i].length()-8);
							m_handler.sendMessage(msg);
						}
						else
						{
							Message msg = new Message();
							msg.what = substr[i].length() == 4 ? -1 : 0;
							msg.obj = "";
							m_handler.sendMessage(msg);
						}
					
					}

				}

				m_bASYC = false;
			}
		});
		thread.start();
	}

	static void StartASYCReadlables() {
		m_bASYC = true;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int nTemp = 0;
				m_nCount = 0;
				while (m_handler != null) {

					nTemp = Read(m_buf, m_nCount, 1024);
					m_nCount += nTemp;
					if (nTemp == 0)
						break;
					String str=reader.BytesToString(m_buf, 0, m_nCount);
					String[]substr=Pattern.compile("A0(.*?)81").split(str); 
					for(int i=0;i<substr.length;i++)
					{
						if(substr[i].length()>4)
						{
							Message msg = new Message();
							msg.what = (substr[i].length()-14)/2;
							msg.obj = substr[i].substring(6, substr[i].length()-8);
							m_handler.sendMessage(msg);
						}
						else
						{
							Message msg = new Message();
							msg.what = substr[i].length() == 4 ? -1 : 0;
							msg.obj = "";
							m_handler.sendMessage(msg);
						}
					
					}

				}

				m_bASYC = false;
			}
		});
		thread.start();
	}

	static void StartASYClables() {
		m_bASYC = true;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int nTemp = 0,nIndex=0;
				boolean tag_find=false;
				m_nCount = 0;
				while (m_handler != null) {  
					nIndex=m_nCount;
					nTemp = Read(m_buf, m_nCount, 10240-m_nCount);
					m_nCount += nTemp;
					if (nTemp == 0)
						break;
					String str=reader.BytesToString(m_buf, nIndex, m_nCount-nIndex);
					String[]substr=Pattern.compile("A0(.*?)89").split(str); 
					for(int i=0;i<substr.length;i++)
					{
						Log.e("777777777777777777777777777", substr[i]);
						if(substr[i].length()>16)
						{
							tag_find = true;
							Message msg = new Message();
							msg.what = (substr[i].length()-10)/2;
							msg.obj = substr[i].substring(6, substr[i].length()-4);
							m_handler.sendMessage(msg);
						}
						else
						{
							
							Message msg = new Message();
							msg.what = substr[i].length() == 4 ? -1 : 0;
							msg.obj = tag_find?"1":"0";
							//msg.obj = "";
							m_handler.sendMessage(msg);
							Log.e("end", "tertretretert");
							tag_find=false;
						}

					}
					if(m_nCount>=1024)  
					m_nCount=0;

				}

				m_bASYC = false;
			}
		});
		thread.start();
	}

	static {
		System.loadLibrary("uhf-tools");
	}

	public static byte[] stringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static String BytesToString(byte[] b, int nS, int ncount) {
		String ret = "";
		int nMax = ncount > (b.length - nS) ? b.length - nS : ncount;
		for (int i = 0; i < nMax; i++) {
			String hex = Integer.toHexString(b[i + nS] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	public static int byteToInt(byte[] b) // byteToInt
	{
		int t2 = 0, temp = 0;
		for (int i = 3; i >= 0; i--) {
			t2 = t2 << 8;
			temp = b[i];
			if (temp < 0) {
				temp += 256;
			}
			t2 = t2 + temp;

		}
		return t2;

	}
	public static int byteToInt(byte[] b,int nIndex) // byteToInt
	{
		int t2 = 0, temp = 0;
		for (int i = 3; i >= 0; i--) {
			t2 = t2 << 8;
			temp = b[i];
			if (temp < 0) {
				temp += 256;
			}
			t2 = t2 + temp;

		}
		return t2;

	}

	/**** int to byte ******/
	public static byte[] intToByte(int content, int offset) {

		byte result[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int j = offset; j < result.length; j += 4) {
			result[j + 3] = (byte) (content & 0xff);
			result[j + 2] = (byte) ((content >> 8) & 0xff);
			result[j + 1] = (byte) ((content >> 16) & 0xff);
			result[j] = (byte) ((content >> 24) & 0xff);
		}
		return result;
	}

}
