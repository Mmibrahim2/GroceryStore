run: FoodMapper.class
	java FoodMapper

FoodMapper.class: compileAll
	javac FoodMapper.java

compileAll: FoodMapperFrontend.class FoodLoader.class FoodMapperBackend.class RedBlackTree.class RedBlackTreeADT.class

FoodMapperFrontend.class: FoodMapperFrontend.java
	javac FoodMapperFrontend.java

FoodLoader.class: FoodLoader.java
	javac FoodLoader.java

FoodMapperBackend.class: FoodMapperBackend.java
	javac FoodMapperBackend.java

RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java

RedBlackTreeADT.class: RedBlackTreeADT.java
	javac RedBlackTreeADT.java

runTests: runFrontendDeveloperTests runBackendTests runDataWranglerTests.class runAlgorithmEngineerTests

runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontDeveloperTests

FrontendDeveloperTests.class: TextUITester.class FoodMapperFrontend.class IFoodMapperBackend.class IFood.class
	javac -cp .:junit5.jar FrontDeveloperTests.java

TextUITester.class:
	javac TextUITester.java

IFoodMapperBackend.class:
	javac FDFoodMapperBackend.java

IFood.class:
	javac FDIFood.java
	javac Food.java

runDataWranglerTests.class: Food.class FoodLoader.class DataWranglerTests.class Foods
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java

Foods:
	javac Food.java
	javac FoodLoader.java

Food.class: Food.java
	javac Food.java

runAlgorithmEngineerTests: compileAlgorithmEngineer
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests

compileAlgorithmEngineer: AlgorithmEngineerTests.class RedBlackTree.class RedBlackTreeADT.class

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

clean:
	rm *.class


runBackendTests: backendCompile
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDeveloperTest

backendCompile: FoodMapperBackend.class BDFood.class RedBlackTree.java
	javac -cp .:junit5.jar BackendDeveloperTest.java

BDFood.class: BDFood.java
	javac BDFood.java

runAlgorithmEngineerTests: compileAlgorithmEngineer
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests
compileAlgorithmEngineer: AlgorithmEngineerTests.class RedBlackTree.class RedBlackTreeADT.class
AlgorithmEngineerTests.class: AlgorithmEngineerTests.java
	 javac -cp .:junit5.jar AlgorithmEngineerTests.java
RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java
RedBlackTreeADT.class: RedBlackTreeADT.java
	javac  RedBlackTreeADT.java
