./gradlew clean
cd docs
mkdir groovy
cd groovy
wget https://bintray.com/artifact/download/groovy/maven/apache-groovy-binary-2.5.4.zip
unzip apache-groovy-binary-2.5.4.zip
pwd
cd ..
pwd
./generateIndex.groovy
cd ..
pwd
./gradlew generateHTML
echo 0