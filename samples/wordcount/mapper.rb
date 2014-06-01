class WordCounterMapper
  def map(key, value, collector)
    value.to_s.split.each do |word|
      collector.collect(word, 1)
    end
  end
end
WordCounterMapper.new
