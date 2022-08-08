# eql-cli

A command line script for executing [EQL](https://github.com/edn-query-language/eql)
queries on [EDN](https://github.com/edn-format/edn) data.

## Install

TODO

## Usage

```bash
eql --query '[:foo]' '{:foo 123 :bar 456}'
```

Returns `{:foo 123}` as the result.

Data is also accepted over STDIN, so you can do the following

```bash
echo '{:foo 123 :bar 456}' | eql --query '[:foo]'
```
