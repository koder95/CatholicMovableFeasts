# CatholicMovableFeasts
A simple and useful generator of important christian dates.

## Usage
### Maven dependency
```xml
<dependency>
    <groupId>pl.koder95</groupId>
    <artifactId>cmf</artifactId>
    <version>0.1.2</version>
</dependency>
```
### Command line execution
Prints a command line help:
```shell
java -jar cmf.jar -h
```
#### Run without arguments (default run)
```shell
java -jar cmf.jar
```
It causes generating a movable feasts row for the current year and saves as JSON file in the current location.
It formats a file name according to the pattern:<a name="default-filename"/>
```regexp
(\d{4})-(\d{2})-(\d{2})_(\d{2})(\d{2})(\d{2})\.cmf\.json
```
e.g. `2024-10-04_150923.cmf.json`.
#### Run with options
Available options:

| Option       | Short | Default<br/>value                      | Description                                                                                                                                                  |
|--------------|-------|----------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `--dir`      | `-d`  | the current directory                  | It determines a directory of an output file.                                                                                                                 |
| `--filename` | `-n`  | [default file name](#default-filename) | It determines a file name of an output file.                                                                                                                 |
| `--format`   | `-f`  | `json`                                 | It determines format for the output file. It is one of:<ul><li>`json`/`prettyjson`</li></ul>                                                                 |
| `--help`     | `-h`  | not applicable                         | It prints help.                                                                                                                                              |
| `--years`    | `-y`  | the current year                       | It determines a list of year for a MFR generator. Numbers can be separated by dot (e.g. `2024.2028.2050`) or determine a range of values (e.g. `2024-2050`). |

