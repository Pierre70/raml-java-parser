/*
 * Copyright 2016 (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.parser.loader;

import java.io.InputStream;

public class ClassPathResourceLoader implements ResourceLoader
{
    ClassLoader customClassLoader;

    public ClassPathResourceLoader()
    {
    }

    public ClassPathResourceLoader(ClassLoader customClassLoader)
    {
        this.customClassLoader = customClassLoader;
    }

    @Override
    public InputStream fetchResource(String resourceName)
    {
        InputStream inputStream = null;
        if (customClassLoader != null)
        {
            inputStream = customClassLoader.getResourceAsStream(resourceName);
        }
        else
        {
            inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
            if (inputStream == null)
            {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
            }
        }
        return inputStream;
    }

}