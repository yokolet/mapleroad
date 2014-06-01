# -*- ruby -*-

require 'rubygems'
require 'rake/javaextensiontask'

Rake::JavaExtensionTask.new('mapleroad') do |ext|
  require 'lock_jar'
  LockJar.lock
  locked_jars = LockJar.load

  jruby_home = ENV['MY_RUBY_HOME'] # this is available on rvm
  jars = ["#{jruby_home}/lib/jruby.jar"] + FileList['lib/*.jar'] + locked_jars
  ext.classpath = jars.map {|x| File.expand_path x}.join ':'
  ext.source_version = '1.7'
  ext.target_version = '1.7'
  ext.name = 'mapleroad_service'
end
