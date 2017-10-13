/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License") +  you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.openmeetings.backup;

import static org.apache.commons.lang3.math.NumberUtils.toInt;

import java.io.Serializable;

import org.apache.openmeetings.util.Version;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "version")
public class BackupVersion implements Serializable, Comparable<BackupVersion> {
	private static final long serialVersionUID = 1L;

	@Element(data = true)
	private int major;

	@Element(data = true)
	private int minor;

	@Element(data = true)
	private int micro;

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public int getMicro() {
		return micro;
	}

	public void setMicro(int micro) {
		this.micro = micro;
	}

	public static BackupVersion get() {
		String ver = Version.getVersion();
		return get(ver);
	}

	public static BackupVersion get(String ver) {
		BackupVersion bv = new BackupVersion();
		String[] dashParts = ver.split("-");
		if (dashParts.length == 0) {
			return bv;
		}
		String[] dotParts = dashParts[0].split("\\.");
		if (dotParts.length > 0) {
			bv.major = toInt(dotParts[0]);
		}
		if (dotParts.length > 1) {
			bv.minor = toInt(dotParts[1]);
		}
		if (dotParts.length > 2) {
			bv.micro = toInt(dotParts[2]);
		}
		return bv;
	}

	@Override
	public int compareTo(BackupVersion o) {
		if (o == null) {
			return 1;
		}
		if (equals(o)) {
			return 0;
		}
		if (major > o.major) {
			return 1;
		}
		if (minor > o.minor) {
			return 1;
		}
		if (micro > o.micro) {
			return 1;
		}
		return -1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + major;
		result = prime * result + micro;
		result = prime * result + minor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BackupVersion other = (BackupVersion) obj;
		if (major != other.major) {
			return false;
		}
		if (micro != other.micro) {
			return false;
		}
		if (minor != other.minor) {
			return false;
		}
		return true;
	}
}
