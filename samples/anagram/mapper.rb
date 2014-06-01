class AnagramMapper
  def map(key, value, collector)
    word = value.to_s
    sorted_word = word.scan(/./).sort.join
    collector.collect(sorted_word, word)
  end
end
AnagramMapper.new
