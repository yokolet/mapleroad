# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'mapleroad/version'

Gem::Specification.new do |spec|
  spec.name          = "mapleroad"
  spec.version       = MapleRoad::VERSION
  spec.authors       = ["Yoko Harada"]
  spec.email         = ["yokolet@gmail.com"]
  spec.summary       = %q{Proof of concept gem for hadoop's mapreduce}
  spec.description   = %q{This gem is a proof of concept that mapper and reducer
                          of mapreduce can be written in Ruby.}
  spec.homepage      = ""
  spec.license       = "MIT"

  spec.files         = `git ls-files -z`.split("\x0")
  spec.executables   = spec.files.grep(%r{^bin/}) { |f| File.basename(f) }
  spec.test_files    = spec.files.grep(%r{^(test|spec|features)/})
  spec.require_paths = ["lib"]

  spec.add_development_dependency "bundler", "~> 1.6"
  spec.add_development_dependency "rake"
end
