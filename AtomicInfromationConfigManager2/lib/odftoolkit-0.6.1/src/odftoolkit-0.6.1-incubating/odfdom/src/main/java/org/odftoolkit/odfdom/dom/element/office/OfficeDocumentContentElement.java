/************************************************************************
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 *
 * Copyright 2008, 2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0. You can also
 * obtain a copy of the License at http://odftoolkit.org/docs/license.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ************************************************************************/

/*
 * This file is automatically generated.
 * Don't edit manually.
 */
package org.odftoolkit.odfdom.dom.element.office;

import org.odftoolkit.odfdom.pkg.OdfElement;
import org.odftoolkit.odfdom.pkg.ElementVisitor;
import org.odftoolkit.odfdom.pkg.OdfFileDom;
import org.odftoolkit.odfdom.pkg.OdfName;
import org.odftoolkit.odfdom.dom.OdfDocumentNamespace;
import org.odftoolkit.odfdom.dom.DefaultElementVisitor;
import org.odftoolkit.odfdom.dom.attribute.grddl.GrddlTransformationAttribute;
import org.odftoolkit.odfdom.dom.attribute.office.OfficeVersionAttribute;

/**
 * DOM implementation of OpenDocument element  {@odf.element office:document-content}.
 *
 */
public class OfficeDocumentContentElement extends OdfElement {

	public static final OdfName ELEMENT_NAME = OdfName.newName(OdfDocumentNamespace.OFFICE, "document-content");

	/**
	 * Create the instance of <code>OfficeDocumentContentElement</code>
	 *
	 * @param  ownerDoc     The type is <code>OdfFileDom</code>
	 */
	public OfficeDocumentContentElement(OdfFileDom ownerDoc) {
		super(ownerDoc, ELEMENT_NAME);
	}

	/**
	 * Get the element name
	 *
	 * @return  return   <code>OdfName</code> the name of element {@odf.element office:document-content}.
	 */
	public OdfName getOdfName() {
		return ELEMENT_NAME;
	}

	/**
	 * Receives the value of the ODFDOM attribute representation <code>GrddlTransformationAttribute</code> , See {@odf.attribute grddl:transformation}
	 *
	 * @return - the <code>String</code> , the value or <code>null</code>, if the attribute is not set and no default value defined.
	 */
	public String getGrddlTransformationAttribute() {
		GrddlTransformationAttribute attr = (GrddlTransformationAttribute) getOdfAttribute(OdfDocumentNamespace.GRDDL, "transformation");
		if (attr != null) {
			return String.valueOf(attr.getValue());
		}
		return null;
	}

	/**
	 * Sets the value of ODFDOM attribute representation <code>GrddlTransformationAttribute</code> , See {@odf.attribute grddl:transformation}
	 *
	 * @param grddlTransformationValue   The type is <code>String</code>
	 */
	public void setGrddlTransformationAttribute(String grddlTransformationValue) {
		GrddlTransformationAttribute attr = new GrddlTransformationAttribute((OdfFileDom) this.ownerDocument);
		setOdfAttribute(attr);
		attr.setValue(grddlTransformationValue);
	}

	/**
	 * Receives the value of the ODFDOM attribute representation <code>OfficeVersionAttribute</code> , See {@odf.attribute office:version}
	 *
	 * Attribute is mandatory.
	 *
	 * @return - the <code>String</code> , the value or <code>null</code>, if the attribute is not set and no default value defined.
	 */
	public String getOfficeVersionAttribute() {
		OfficeVersionAttribute attr = (OfficeVersionAttribute) getOdfAttribute(OdfDocumentNamespace.OFFICE, "version");
		if (attr != null) {
			return String.valueOf(attr.getValue());
		}
		return null;
	}

	/**
	 * Sets the value of ODFDOM attribute representation <code>OfficeVersionAttribute</code> , See {@odf.attribute office:version}
	 *
	 * @param officeVersionValue   The type is <code>String</code>
	 */
	public void setOfficeVersionAttribute(String officeVersionValue) {
		OfficeVersionAttribute attr = new OfficeVersionAttribute((OdfFileDom) this.ownerDocument);
		setOdfAttribute(attr);
		attr.setValue(officeVersionValue);
	}

	/**
	 * Create child element {@odf.element office:automatic-styles}.
	 *
	 * @return the element {@odf.element office:automatic-styles}
	 */
	public OfficeAutomaticStylesElement newOfficeAutomaticStylesElement() {
		OfficeAutomaticStylesElement officeAutomaticStyles = ((OdfFileDom) this.ownerDocument).newOdfElement(OfficeAutomaticStylesElement.class);
		this.appendChild(officeAutomaticStyles);
		return officeAutomaticStyles;
	}

	/**
	 * Create child element {@odf.element office:body}.
	 *
	 * Child element is mandatory.
	 *
	 * @return the element {@odf.element office:body}
	 */
	public OfficeBodyElement newOfficeBodyElement() {
		OfficeBodyElement officeBody = ((OdfFileDom) this.ownerDocument).newOdfElement(OfficeBodyElement.class);
		this.appendChild(officeBody);
		return officeBody;
	}

	/**
	 * Create child element {@odf.element office:font-face-decls}.
	 *
	 * @return the element {@odf.element office:font-face-decls}
	 */
	public OfficeFontFaceDeclsElement newOfficeFontFaceDeclsElement() {
		OfficeFontFaceDeclsElement officeFontFaceDecls = ((OdfFileDom) this.ownerDocument).newOdfElement(OfficeFontFaceDeclsElement.class);
		this.appendChild(officeFontFaceDecls);
		return officeFontFaceDecls;
	}

	/**
	 * Create child element {@odf.element office:scripts}.
	 *
	 * @return the element {@odf.element office:scripts}
	 */
	public OfficeScriptsElement newOfficeScriptsElement() {
		OfficeScriptsElement officeScripts = ((OdfFileDom) this.ownerDocument).newOdfElement(OfficeScriptsElement.class);
		this.appendChild(officeScripts);
		return officeScripts;
	}

	@Override
	public void accept(ElementVisitor visitor) {
		if (visitor instanceof DefaultElementVisitor) {
			DefaultElementVisitor defaultVisitor = (DefaultElementVisitor) visitor;
			defaultVisitor.visit(this);
		} else {
			visitor.visit(this);
		}
	}
}
