class WordCounterReducer
  def reduce(key, values, collector)
    sum = values.inject(0) do |memo, v|
      memo = memo + v.to_string.to_i
    end
    collector.collect(key, sum)
  end
end
WordCounterReducer.new
