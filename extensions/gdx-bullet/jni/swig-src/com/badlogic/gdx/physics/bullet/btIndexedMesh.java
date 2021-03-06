/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet;

public class btIndexedMesh {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected btIndexedMesh (long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	public static long getCPtr (btIndexedMesh obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize () {
		delete();
	}

	public synchronized void delete () {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				gdxBulletJNI.delete_btIndexedMesh(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setM_numTriangles (int value) {
		gdxBulletJNI.btIndexedMesh_m_numTriangles_set(swigCPtr, this, value);
	}

	public int getM_numTriangles () {
		return gdxBulletJNI.btIndexedMesh_m_numTriangles_get(swigCPtr, this);
	}

	public void setM_triangleIndexBase (SWIGTYPE_p_unsigned_char value) {
		gdxBulletJNI.btIndexedMesh_m_triangleIndexBase_set(swigCPtr, this, SWIGTYPE_p_unsigned_char.getCPtr(value));
	}

	public SWIGTYPE_p_unsigned_char getM_triangleIndexBase () {
		long cPtr = gdxBulletJNI.btIndexedMesh_m_triangleIndexBase_get(swigCPtr, this);
		return (cPtr == 0) ? null : new SWIGTYPE_p_unsigned_char(cPtr, false);
	}

	public void setM_triangleIndexStride (int value) {
		gdxBulletJNI.btIndexedMesh_m_triangleIndexStride_set(swigCPtr, this, value);
	}

	public int getM_triangleIndexStride () {
		return gdxBulletJNI.btIndexedMesh_m_triangleIndexStride_get(swigCPtr, this);
	}

	public void setM_numVertices (int value) {
		gdxBulletJNI.btIndexedMesh_m_numVertices_set(swigCPtr, this, value);
	}

	public int getM_numVertices () {
		return gdxBulletJNI.btIndexedMesh_m_numVertices_get(swigCPtr, this);
	}

	public void setM_vertexBase (SWIGTYPE_p_unsigned_char value) {
		gdxBulletJNI.btIndexedMesh_m_vertexBase_set(swigCPtr, this, SWIGTYPE_p_unsigned_char.getCPtr(value));
	}

	public SWIGTYPE_p_unsigned_char getM_vertexBase () {
		long cPtr = gdxBulletJNI.btIndexedMesh_m_vertexBase_get(swigCPtr, this);
		return (cPtr == 0) ? null : new SWIGTYPE_p_unsigned_char(cPtr, false);
	}

	public void setM_vertexStride (int value) {
		gdxBulletJNI.btIndexedMesh_m_vertexStride_set(swigCPtr, this, value);
	}

	public int getM_vertexStride () {
		return gdxBulletJNI.btIndexedMesh_m_vertexStride_get(swigCPtr, this);
	}

	public void setM_indexType (int value) {
		gdxBulletJNI.btIndexedMesh_m_indexType_set(swigCPtr, this, value);
	}

	public int getM_indexType () {
		return gdxBulletJNI.btIndexedMesh_m_indexType_get(swigCPtr, this);
	}

	public void setM_vertexType (int value) {
		gdxBulletJNI.btIndexedMesh_m_vertexType_set(swigCPtr, this, value);
	}

	public int getM_vertexType () {
		return gdxBulletJNI.btIndexedMesh_m_vertexType_get(swigCPtr, this);
	}

	public btIndexedMesh () {
		this(gdxBulletJNI.new_btIndexedMesh(), true);
	}

}
