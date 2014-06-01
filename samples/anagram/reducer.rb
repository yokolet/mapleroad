class AnagramReducer
  def reduce(key, values, collector)
    output = values.inject("") do |memo, v|
      memo = memo + v.to_s + "~"
    end
    if (output.split("~").length >= 2)
      collector.collect(key.to_s, output.gsub("~", ","))
    end
  end
end
AnagramReducer.new
