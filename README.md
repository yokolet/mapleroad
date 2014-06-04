# MapleRoad

This gem is a proof of concept to write Hadoop's mapreduce code in Ruby.
This gem is created inspired by *jmapreduce* gem, [https://bitbucket.org/abhinaymehta/jmapreduce](https://bitbucket.org/abhinaymehta/jmapreduce).


The mapleroad gem works only on JRuby.
Also, this gem assumes hadoop command is on the PATH and can use it just type `hadoop`.
Prior to trying this gem, setup Hadoop's cluster(s) and check some Hadoop sample works fine.
Additionally, copy jruby.jar to Hadoop's mapred lib directory. For example, `/opt/cloudera/parcels/CDH-5.0.0-1.cdh5.0.0.p0.47/lib/hadoop-mapreduce/`.

## Installation

   [Note] The mapleroad gem is not yet in the gem style. You can use `gem install` or `bundle` to download whole lot, however, it doesn' make any difference from cloning this repo.



Add this line to your application's Gemfile:

    gem 'mapleroad'

And then execute:

    $ bundle

Or install it yourself as:

    $ gem install mapleroad


## Usage

1. Write mapper class. Make sure mapper class file returns an instance of mapper class when the file is evaluated. The mapper class must have map method whose arguments are key, value, and collector.

2. Writer reducer class. Like mapper class, make sure to return reducer class instance. The reducer class must have reduce method whose arguments are key, value, and collector.

3. Put input file(s) to the hadoop filesystem

4. Run bin/mapleroad command with arguments

    Example: bin/mapleroad mapper.rb reducer.rb my-dir/input my-dir/output Text:Int

  - The first arg: mapper file name
  - The second arg: reducer filename
  - The third arg: input
  - The fourth arg: output
  - The fifth arg: types of output's key/value. Text:Text or Text:Int only for now

## Samples

This gem includes two samples, anagram and wordcount.

The original anagram examples is found at [https://code.google.com/p/hadoop-map-reduce-examples/wiki/Anagram_Example](https://code.google.com/p/hadoop-map-reduce-examples/wiki/Anagram_Example).
The data for input can be download from that wiki page.

Suppose you are in the top directory of this gem,

`bin/mapleroad samples/anagram/mapper.rb samples/anagram/reducer.rb anagram/input anagram/output Text:Text`


The wordcount example is a famous Hadoop example.
As it is explained at [http://www.cloudera.com/content/cloudera-content/cloudera-docs/HadoopTutorial/CDH4/Hadoop-Tutorial/ht_usage.html](http://www.cloudera.com/content/cloudera-content/cloudera-docs/HadoopTutorial/CDH4/Hadoop-Tutorial/ht_usage.html), create two input files.

`bin/mapleroad samples/wordcount/mapper.rb samples/wordcount/reducer.rb wordcount/input wordcount/output Text:Int`


## Why the name is mapleroad?

Because it sounds something like m-r.

The Maple Road actually exists in Detroit suburb, where I used to live.
People sometines call it 15 Mile.
Since this major road runs from east to west, and a mile north of 14 Mile.
You may know 8 Mile by Eminem's movie.
In the Detroit area, major roads that runs east to west have names, 7 Mile, 8 Mile, ..., 12 Mile, and 14 Mile.
Each Mile road apart about a mile and runs parallel.
I used to drive Maple Road (aka 15 Mile) almost everyday.

Also, the color of maple leaves are the reason.
Those turn to beautiful red in fall, which makes me think of the color of Ruby.


## Contributing

1. Fork it ( https://github.com/yokolet/mapleroad/fork )
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create a new Pull Request
