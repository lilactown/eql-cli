# eql-cli

A command line script for executing [EQL](https://github.com/edn-query-language/eql)
queries on [EDN](https://github.com/edn-format/edn) data.
Powered by [pyramid](https://github.com/lilactown/pyramid) and [babashka](https://github.com/babashka/babashka).


## Install

### Homebrew

```bash
brew install lilactown/brew/eql
```

### Manual

* Install [babashka](https://github.com/babashka/babashka)
* Download the [eql](./eql) script and place it on your `PATH`.

## Usage

```bash
eql --query '[:foo]' '{:foo 123 :bar 456}'
```

Returns `{:foo 123}` as the result.

Data is also accepted over STDIN, so you can do the following

```bash
echo '{:foo 123 :bar 456}' | eql --query '[:foo]'
```


## Usecases

EQL over arbitrary data can be useful as a "`select-keys` on steroids" for deep
nested data.


```bash
curl -s https://pokeapi.co/api/v2/pokemon/ditto | # fetch some data
jet --from json --to edn --no-pretty --keywordize | # convert it from JSON to EDN
eql --query "[:id :name :height :weight \
             {:abilities [{:ability [:name]}]}]" | # select just the keys we want
jet # pretty print
```

Outputs

```clojure
{:abilities [{:ability {:name "limber"}} {:ability {:name "imposter"}}],
 :height 3,
 :id 132,
 :name "ditto",
 :weight 40}
```
