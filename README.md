# XZing-Barcode-Scanner-Minified-Eclipse
Minified version of XZing libray for integration in Android projects in eclipse and adt.

 This is rewrite of https://github.com/journeyapps/zxing-android-embedded for eclipse projects.
 
 The idea is to add standalone barcode scanner in any android project without adding tons of packages. 
 
 I tried creating jars but this is not how it works for android projects as discussed here http://stackoverflow.com/questions/17063826/how-to-create-jar-for-android-library-project .And i really dont want to add lib project as it will complicate my continous integration process.. too much work.
 
 Then I tried making AAR but then I its impossible to use Studio in Nuclear Rector level secured ODCs, you know how gradle sucks ie works. 
 
 So I came up with minimized version of zXing embeded project which do the same trick without making my project look huge ie add 3 packages  2 resource layout few strings and here we go !!
