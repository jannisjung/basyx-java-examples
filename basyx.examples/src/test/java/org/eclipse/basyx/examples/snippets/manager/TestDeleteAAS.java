/*******************************************************************************
 * Copyright (C) 2021 the Eclipse BaSyx Authors
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * SPDX-License-Identifier: MIT
 ******************************************************************************/
package org.eclipse.basyx.examples.snippets.manager;

import static org.junit.Assert.fail;

import org.eclipse.basyx.examples.snippets.AbstractSnippetTest;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IIdentifier;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.vab.exception.provider.ResourceNotFoundException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for the DeleteAAS snippet
 * 
 * @author conradi
 *
 */
public class TestDeleteAAS extends AbstractSnippetTest {
	
	private Logger logger = LoggerFactory.getLogger(TestDeleteAAS.class);

	@Test
	public void testDeleteAAS() {

		// Get the Identifier of the example AAS
		IIdentifier aasIdentifier = new Identifier(IdentifierType.CUSTOM, AAS_ID);

		// Delete the AAS
		deleteAas(aasIdentifier);

		// Try to retrieve deleted AAS; should throw ResourceNotFoundException
		try {
			RetrieveAAS.retrieveRemoteAAS(aasIdentifier, registryComponent.getRegistryPath());
			fail();
		} catch (ResourceNotFoundException e) {
		}

	}

	private void deleteAas(IIdentifier aasIdentifier) {
		try {
			DeleteAAS.deleteAAS(aasIdentifier, registryComponent.getRegistryPath());
		} catch (ResourceNotFoundException e) {	
			logger.info("The AAS with id {} has already been unregistered from registry.", aasIdentifier.getId());
		}
	}

}
