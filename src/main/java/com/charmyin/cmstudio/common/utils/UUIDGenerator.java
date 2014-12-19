package com.charmyin.cmstudio.common.utils;


@SuppressWarnings("all")
public class UUIDGenerator {
	/**
	 * @return the map for mac address to ip address set for local box.
	 */
	private static final java.util.Map<java.lang.String, java.util.Set<java.lang.String>> getMacToIpMap() {
		return mapMacToIpes;
	}

	/**
	 * @return the mac set for local box.
	 */
	private static final java.util.Set<java.lang.String> getMacSet() {
		return macSet;
	}

	/**
	 * @return the ip set for local box.
	 */
	private static final java.util.Set<java.lang.String> getIpSet() {
		return ipSet;
	}

	/**
	 * @return the process Id for this JVM.
	 */
	private static long getProcessId() {
		return procId;
	}

	public static java.lang.String generate() {
		java.lang.StringBuffer buf = new java.lang.StringBuffer();
		buf.append(java.util.UUID.randomUUID());
		buf.append(genAddon());
		for (int i = buf.length() - 1; i >= 0; i--) {
			if (buf.charAt(i) == '-')
				buf.deleteCharAt(i);
		}
		while (buf.length() > UUID_STR_LEN)
			buf.deleteCharAt(UUID_STR_LEN);
		while (buf.length() < UUID_STR_LEN)
			buf.append('0');
		return new java.lang.String(buf);
	}

	private byte[] toUuidBinary(final java.lang.String uuidStr) {
		if (uuidStr == null)
			throw new java.lang.RuntimeException("The parameter uuidStr is null.");
		if (uuidStr.length() != UUID_STR_LEN)
			throw new java.lang.RuntimeException("The length of parameter uuidStr is not " + UUID_STR_LEN);
		byte[] uuidBinary = new byte[UUID_BIN_LEN];
		for (int i = 0; i < UUID_BIN_LEN; i++)
			uuidBinary[i] = (byte) (java.lang.Short.parseShort(uuidStr.substring(i << 1, (i + 1) << 1), 16) & 0x0ff);
		return uuidBinary;
	}

	private java.lang.String toUuidString(final byte[] uuidBinary) {
		if (uuidBinary == null)
			throw new java.lang.RuntimeException("parameter uuidBinary is null.");
		if (uuidBinary.length != UUID_BIN_LEN)
			throw new java.lang.RuntimeException("The length of parameter uuidBinary is not " + UUID_BIN_LEN);
		java.lang.StringBuffer buf = new java.lang.StringBuffer();
		for (int i = 0; i < UUID_BIN_LEN; i++) {
			java.lang.String str = java.lang.Integer.toHexString(uuidBinary[i] & 0x0ff);
			while (str.length() < 2)
				str = "0" + str;
			buf.append(str.substring(0, 2));
		}
		return new java.lang.String(buf);
	}

	private java.util.List<java.lang.Exception> getExceptions() {
		return exceptions;
	}

	private static java.lang.String genAddon() {
		java.lang.StringBuffer buf = new java.lang.StringBuffer();

		buf.append('/');
		buf.append(java.lang.Thread.currentThread().getId());

		buf.append('/');
		buf.append(procId);

		for (java.lang.String mac : macSet) {
			buf.append('/');
			buf.append(mac);
		}
		;

		for (java.lang.String ip : ipSet) {
			buf.append('/');
			buf.append(ip);
		}
		;

		final java.lang.String bufStr = new java.lang.String(buf);
		java.lang.String addon = java.lang.Integer.toHexString(hashToInt(bufStr.getBytes()));
		while (addon.length() < 8)
			addon = "0" + addon;
		addon = "-" + addon;
		return addon;
	}

	private static int hashToInt(final byte[] bytes) {
		int hval = 0;
		if (bytes != null) {
			for (int i = 0; i < bytes.length; i++) {
				hval += (hval << 1) + (hval << 4) + (hval << 7) + (hval << 8) + (hval << 24);
				hval ^= bytes[i] & 0x0ff;
			}
		}
		return hval;
	}

	private static void resolveMacedIpes(java.util.Map<java.lang.String, java.util.Set<java.lang.String>> mapMacToIpes,
			java.util.Set<java.lang.String> macSet, java.util.Set<java.lang.String> ipSet,
			java.util.List<java.lang.Exception> exceptionHolder) {
		try {
			resolveMacedIpes(java.net.NetworkInterface.getNetworkInterfaces(), mapMacToIpes, exceptionHolder);

			if (macSet != null) {
				macSet.clear();
				macSet.addAll(mapMacToIpes.keySet());
			}

			if (ipSet != null) {
				ipSet.clear();
				for (final java.lang.String mac : macSet) {
					final java.util.Set<java.lang.String> subIpSet = mapMacToIpes.get(mac);
					if (subIpSet != null)
						for (final java.lang.String ip : subIpSet)
							if (!ipSet.contains(ip))
								ipSet.add(ip);
				}
			}
		} catch (java.net.SocketException e) {
			if (exceptionHolder != null)
				exceptionHolder.add(e);
		}
	}

	private static void resolveMacedIpes(final java.util.Enumeration<java.net.NetworkInterface> nis,
			java.util.Map<java.lang.String, java.util.Set<java.lang.String>> mapMacToIpes,
			java.util.List<java.lang.Exception> exceptionHolder) {
		if (nis == null)
			return;
		while (nis.hasMoreElements()) {
			java.net.NetworkInterface ni = nis.nextElement();
			if (ni != null) {
				try {
					byte[] macBytes = ni.getHardwareAddress();
					if (macBytes != null && macBytes.length > 0) {
						java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
						java.io.PrintWriter out = new java.io.PrintWriter(baos);
						for (int i = 0; i < macBytes.length; i++)
							out.format("%02X%s", macBytes[i], (i < macBytes.length - 1) ? "-" : "");
						out.flush();
						java.lang.String mac = new java.lang.String(baos.toByteArray());
						out.close();
						baos.close();
						java.util.List<java.net.InterfaceAddress> ifAddres = ni.getInterfaceAddresses();
						if (ifAddres != null)
							for (java.util.Iterator<java.net.InterfaceAddress> it = ifAddres.iterator(); it.hasNext();) {
								java.net.InterfaceAddress ifAddr = it.next();
								if (ifAddr != null) {
									java.net.InetAddress iAddr = ifAddr.getAddress();
									if (!iAddr.isLoopbackAddress()) {
										java.lang.String ip = iAddr.getHostAddress();
										//
										java.util.Set<java.lang.String> ipes = mapMacToIpes.get(mac);
										if (ipes == null) {
											ipes = new java.util.TreeSet<java.lang.String>();
											mapMacToIpes.put(mac, ipes);
										}
										if (!ipes.contains(ip))
											ipes.add(ip);
									}
								}
							}
						resolveMacedIpes(ni.getSubInterfaces(), mapMacToIpes, exceptionHolder);
					}
				} catch (java.net.SocketException e) {
					if (exceptionHolder != null)
						exceptionHolder.add(e);
				} catch (java.io.IOException e) {
					exceptionHolder.add(e);
				}
			}
		}
	}

	private static final int UUID_BIN_LEN = 20;
	private static final int UUID_STR_LEN = UUID_BIN_LEN * 2;
	private static java.util.Map<java.lang.String, java.util.Set<java.lang.String>> mapMacToIpes = new java.util.TreeMap<java.lang.String, java.util.Set<java.lang.String>>();
	private static java.util.Set<java.lang.String> macSet = new java.util.TreeSet<java.lang.String>();
	private static java.util.Set<java.lang.String> ipSet = new java.util.TreeSet<java.lang.String>();
	private static java.util.List<java.lang.Exception> exceptions = new java.util.ArrayList<java.lang.Exception>();
	private static long procId = -1;
	static {
		try {
			resolveMacedIpes(mapMacToIpes, macSet, ipSet, exceptions);
			final java.lang.String jvmId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
			procId = Long.parseLong(jvmId.substring(0, jvmId.indexOf('@')));
		} catch (java.lang.Exception e) {
			exceptions.add(e);
		}
	}
	public static void main(String[] args) {
		System.out.println(UUIDGenerator.generate());
	}
}
