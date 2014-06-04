Description
===========
__no-jb-policy__ is a sample project which demonstrates the way to overcome an issue with ActionBar in tabs navigation mode for Android JellyBean or higher. The issue is that height can't be set to more than 48dip in a portrait mode. Java reflection api is used, so there is no garantee, that this solution will work for all devices.

Another way is to use _targetSdkVersion="15"_ in _AndroidManifest.xml_

License
=======
Code is licensed under WTFPL 2.0 license
