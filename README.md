# AP CSA Mini‑Project: Data Analysis with Arrays & File Input
### Using CSV Files • Arrays of Objects • Algorithms • Data Ethics & Quality

---

## 📌 Project Overview
In this mini‑project, you will choose a dataset (CSV file), design a **custom class** to represent each row, read the dataset using the **Scanner** class, store all data as **objects** in an ArrayList or array, and answer a **guiding question** by analyzing the data.

This project reinforces:

- Arrays & ArrayLists  
- File input with `Scanner`  
- Class design (attributes, constructors, methods)  
- Algorithms (min, max, average, filtering)  
- Data quality & ethics  
- Documentation using **Javadoc**  
- Creating a **UML class diagram**  

By the end, you will produce insights and answer your original question using your program.

---

## 🎯 Your Task
You will:

1. **Choose a dataset** (teacher provided or public).  
2. **Write a guiding question** for your dataset.  
3. **Design a Java class** with ≥ 3 attributes.  
4. **Use `Scanner` to read a CSV file**, parse rows, and construct objects.  
5. **Store all objects** in an array or ArrayList.  
6. **Analyze the dataset** using algorithms you create.  
7. **Print insights** and answer your guiding question.  
8. **Document your code** with Javadoc.  
9. **Create a UML class diagram** representing your custom class.  

Optional stretch challenges are included at the bottom!

---

## 🧪 Example Questions You Might Ask
Your dataset might allow you to answer things like:

- *"Which Pokémon type has the highest average Attack?"*  
- *"Which U.S. state had the lowest obesity rate in 2020?"*  
- *"What country had the highest CO₂ emissions in 2000?"*  
- *"What is the average HP for Fire-type Pokémon?"*  

Think simple, clear, and answerable.

---

## 📁 Project Structure
Your repository should follow this structure:
```
/src
    Main.java
    YourClass.java
/data
    your_dataset.csv
README.md   ← this file
UML_Diagram.png (or UML_Diagram.pdf)
```

---

## 🧩 Step 1 — Choose Your Dataset

**Dataset Name:**  pokemon.csv
**Source / Link:**  https://runestone.academy/ns/books/published/csawesome2/external/_static/datasets/pokemon.csv

**What this dataset contains (2–3 sentences):**  
The data set contains information about each pokemon such as its name, type 1, type 2, HP, attack, and speed. It also includes a link with a photo of the pokemon.  

---

## ❓ Step 2 — Write Your Guiding Question

Your guiding question should be something you can answer using your dataset.

**My guiding question:**  
What is the most common primary and secondary type for the pokemon?  

Examples:

- "Which Pokémon has the highest HP?"  
- "What is the average life expectancy in this dataset?"  
- "Which state had the highest vaccination rate?"  

---

## 🧱 Step 3 — Design Your Class

You must create a class that represents **one row** of your dataset.

### ✔ Your class must include:

- **At least 3 private attributes**  
- **A constructor** that takes all attributes as parameters  
- **Getter methods** for attributes you plan to analyze  
- **`toString()`** for easy printing  
- Any additional analysis/helper methods as needed  

### ✅ My class design (`Pokemon`)

This class represents one row from `pokemon.csv`.

**Private attributes:**
- `name : String`
- `primaryType : String`
- `secondaryType : String`
- `total : int`

**Constructor:**
- `Pokemon(String name, String primaryType, String secondaryType, int total)`

**Getters used for analysis:**
- `getName()`
- `getPrimaryType()`
- `getSecondaryType()`
- `getTotal()`

**Helper method:**
- `hasSecondaryType()`

**String output:**
- `toString()`

### ✏ Class Diagram (UML)

```text
+-----------------------------------------------+
|                    Pokemon                    |
+-----------------------------------------------+
| - name: String                                |
| - primaryType: String                         |
| - secondaryType: String                       |
| - total: int                                  |
+-----------------------------------------------+
| + Pokemon(name: String, primaryType: String,  |
|           secondaryType: String, total: int)  |
| + getName(): String                           |
| + getPrimaryType(): String                    |
| + getSecondaryType(): String                  |
| + getTotal(): int                             |
| + hasSecondaryType(): boolean                 |
| + toString(): String                          |
+-----------------------------------------------+
```


---

## 📥 Step 4 — Read Your CSV File Using Scanner

In `Main.java`, you must:

- Create a `File` object  
- Use `Scanner` to read the file  
- Skip the header row (if needed)  
- Read each line  
- Split by commas using `.split(",")`  
- Trim whitespace  
- Parse numbers using `Integer.parseInt()` or `Double.parseDouble()`  
- Construct objects  
- Add them to an ArrayList or array  

### Column → Attribute Map

| Attribute Name | CSV Column Name | Column Index # | Notes |
|----------------|------------------|----------------|-------|
| name           | Name             | 0              | String text value |
| primaryType    | Type 1           | 1              | String text value |
| secondaryType  | Type 2           | 2              | May be empty (`""`) |
| total          | Total            | 3              | Parse as `int` |

---

## 📊 Step 5 — Analyze Your Data

You must write **at least two algorithms** to analyze your dataset.

### Required: Choose 2 or more algorithms
- [ ] Minimum value of attribute  
- [x] Maximum value of attribute  
- [x] Average of attribute  
- [ ] Filter by category  
- [x] Count items matching a condition  

**Algorithms I will implement:**

1. Find the Pokémon with the highest `total` stat (maximum).  
2. Compute the average `total` stat across all Pokémon (average).  
3. Count frequencies of `primaryType` and `secondaryType` to find the most common type (count by category).  

Optional extras:  
- Sorting  
- Top/bottom N items  
- Grouping by category  
- Comparison between groups  

---

## 🧠 Step 6 — Insights & Answer to Your Question

After analyzing your objects, print:

- ✔ How many rows were loaded  
- ✔ Your algorithm results  
- ✔ A clear answer to your guiding question  

**My findings:**  
Rows loaded: 12.  
Most common primary type: Water (3); most common secondary type: Poison (3).  
Average total stat: 380.75, and the Pokémon with the highest total is Charizard (534).  

**My answer to the guiding question:**  
In this dataset, the most common primary type is **Water (3)**.  
The most common secondary type is **Poison (3)**.  

---

## 📝 Step 7 — Documentation Requirements

### ✅ Javadoc Comments (Completed)

I added Javadoc for:

- Every class (`App` and `Pokemon`)  
- Every method used in the project  
- Every parameter (`@param`)  
- Every return value (`@return`) where applicable  

### ✅ UML Class Diagram (Completed)

I included the UML class diagram for `Pokemon` in Step 3 with:

- Class name  
- Attributes  
- Methods  
- Visibility (`+` public, `-` private)  

If needed for submission format, I can also export this diagram as a separate `UML_Diagram.png` or `.pdf` file.

---

## 🛡 Step 8 — Data Ethics & Quality Reflection
Write a short reflection (3–5 sentences):

- What data-quality issues did you find?
- Could your dataset be biased?
- How might incomplete or inaccurate data affect results?
- How trustworthy are your insights?

**My reflection:**  
An issue found was that some pokemon were missing secondary types which can effect the count. The dataset could be biased because it doesnt include updates about the pokemon. If information is missing in inacurate the results could be wrong.  

---

## ⭐ Optional Challenges (Not Required but Fun!)

### 🔹 User Input
Allow the user to choose:

- Which attribute to analyze
- Which category to filter
- What statistics they want to calculate

### 🔹 Additional Algorithms

- Sorting objects
- Multiple comparisons
- Generating summaries
- Exporting results to a file

### 🔹 Data Cleaning

- Skip rows with missing values
- Detect invalid entries
- Normalize units

---

## ✅ Submission Checklist

- [x] Dataset selected
- [x] Guiding question written
- [x] Class created with ≥3 attributes
- [x] File reading implemented
- [x] ArrayList/array of objects created
- [x] At least 2 analysis algorithms implemented
- [x] Findings printed
- [x] Javadoc comments added
- [x] UML diagram included
- [x] Reflection completed
- [x] Code compiles & runs

---

Good luck, and have fun exploring your dataset! 🚀  
You're now doing real data analysis — just like professional software engineers.
